package com.example.chemistryapp.Controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElementDataScraper {
    private static final String BASE_URL = "https://periodictable.com/Elements/";

    public static ElementData scrapeElementData(int atomicNumber) {
        String url = BASE_URL + String.format("%03d", atomicNumber) + "/data.html";
        ElementData elementData = new ElementData("", "", atomicNumber);

        try {
            Document doc = Jsoup.connect(url)
                    .timeout(10000)
                    .get();

            Elements propertyRows = doc.select("table[cellspacing=5] tr");

            // Extraction of density, melting point and boiling point
            String density = "0";
            String meltingPoint = "0";
            String boilingPoint = "0";

            for (Element row : propertyRows) {
                Elements tds = row.select("td");
                if (tds.size() >= 2) {
                    Element labelCell = tds.get(0);
                    Element valueCell = tds.get(1);

                    String normalizedLabel = labelCell.text().toLowerCase().replaceAll("[^a-z]", "");

                    if (normalizedLabel.contains("density")) {
                        density = valueCell.text().replaceAll("\"", "").replaceAll("\\[.*?\\]", "").trim();
                    } else if (normalizedLabel.contains("meltingpoint")) {
                        meltingPoint = valueCell.text().replaceAll("\"", "").trim();
                    } else if (normalizedLabel.contains("boilingpoint")) {
                        boilingPoint = valueCell.text().replaceAll("\"", "").trim();
                        break;
                    }
                }
            }

            elementData.setDensity(density);
            elementData.setMeltingPoint(meltingPoint);
            elementData.setBoilingPoint(boilingPoint);

            // Extraction of Atomic Weight, Group, Period, Electron Configuration
            Map<String, String> properties = new HashMap<>();
            Elements rows = doc.select("table tbody tr");
            for (Element row : rows) {
                Elements cells = row.select("td");
                if (cells.size() >= 2) {
                    String key = cells.get(0).text().trim();
                    String value = cells.get(1).text().trim();
                    if (!key.isEmpty() && !value.isEmpty()) {
                        properties.put(key, value);
                    }
                }
            }

            if (elementData.getAtomicWeight() == null && properties.containsKey("Atomic Weight")) {
                elementData.setAtomicWeight(properties.get("Atomic Weight"));
            }
            if (properties.containsKey("Group")) {
                elementData.setGroup(properties.get("Group"));
            }
            if (properties.containsKey("Period")) {
                elementData.setPeriod(properties.get("Period"));
            }
            if (properties.containsKey("Electron Configuration")) {
                elementData.setElectronConfiguration(properties.get("Electron Configuration"));
            }

        } catch (IOException e) {
            System.err.println("Error fetching data for atomic number " + atomicNumber + ": " + e.getMessage());
            elementData.setDescription("Error loading data: " + e.getMessage());
        }

        // Extract technical data
        try {
            String mainPageUrl = BASE_URL + String.format("%03d", atomicNumber) + "/";
            Document mainDoc = Jsoup.connect(mainPageUrl)
                    .timeout(10000)
                    .get();

            Element h1 = mainDoc.selectFirst("h1");
            if (h1 != null) {
                Element td = h1.parent();
                if (td != null) {
                    StringBuilder descriptionBuilder = new StringBuilder();
                    boolean foundBr = false;

                    for (Node node : td.childNodes()) {
                        if (node instanceof Element) {
                            Element el = (Element) node;
                            if (el.tagName().equals("table")) {
                            } else if (el.tagName().equals("br")) {
                                foundBr = true;
                            } else if (foundBr) {
                                descriptionBuilder.append(el.text()).append(" ");
                            }
                        } else if (node instanceof TextNode && foundBr) {
                            descriptionBuilder.append(((TextNode) node).text());
                        }
                    }

                    String description = descriptionBuilder.toString().replaceAll("\\s+", " ").trim();
                    elementData.setDescription(description);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        return elementData;
    }
}