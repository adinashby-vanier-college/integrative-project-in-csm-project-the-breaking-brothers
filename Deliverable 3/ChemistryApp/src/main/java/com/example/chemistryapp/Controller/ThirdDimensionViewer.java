package com.example.chemistryapp.Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.scene.transform.Rotate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ThirdDimensionViewer {

    private static final double scaleFactor = 18; // constant to change distance between atoms
    private double mouseX, mouseY;  // stores initial mouse x y position

    public void run(String molecule, Scene initialScene) {
        Stage newStage = new Stage();
        newStage.initOwner(initialScene.getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoleculeViewer3D.fxml"));
        try {

            AnchorPane root = loader.load();
            newStage.setScene(new Scene(root, 600, 400));
            newStage.setTitle("Molecule Viewer");
            newStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int cid = getCID(molecule);
            if (cid != -1) {
                getMoleculeSDF(cid);

                List<Atom> atoms = SDFParser.parseSDF("molecule.sdf");

                // Get the molecule data
                List<int[]> bonds = parseBonds("molecule.sdf");

                if (!atoms.isEmpty()) {
                    System.out.println("not empty");
                    show3DMolecule(newStage, atoms, bonds);
                } else {
                    System.out.println("No atoms found in SDF file.");
                }
            } else {
                System.out.println("CID not found for " + molecule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List<int[]> parseBonds(String filename) throws IOException {
        List<int[]> bonds = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int atomCount = 0, bondCount = 0;
        int lineIndex = 0;

        // only get the bond data
        while ((line = reader.readLine()) != null) {
            lineIndex++;
            if (lineIndex == 4) { // line to read amount of atoms
                String[] parts = line.trim().split("\\s+");
                atomCount = Integer.parseInt(parts[0]);
                bondCount = Integer.parseInt(parts[1]);
            }
            // starts reading bond info
            else if (lineIndex > 4 + atomCount && lineIndex <= 4 + atomCount + bondCount) {
                String[] parts = line.trim().split("\\s+");
                int atom1 = Integer.parseInt(parts[0]) - 1; // start from 0
                int atom2 = Integer.parseInt(parts[1]) - 1; // start from 0
                bonds.add(new int[]{atom1, atom2});
            }
        }
        reader.close();
        return bonds;
    }

    private void show3DMolecule(Stage primaryStage, List<Atom> atoms, List<int[]> bonds) {
        Group moleculeGroup = new Group();

        // center of molecule = average of x y positions
        double sumX = 0, sumY = 0;
        for (Atom atom : atoms) {
            sumX += atom.x;
            sumY += atom.y;
        }
        int atomCount = atoms.size();
        double centerX = sumX / atomCount;
        double centerY = sumY / atomCount;

        // center position found
        System.out.println("Molecule Center: (" + centerX + ", " + centerY + ")");

        // Create spheres for each atom and position them relative to the center
        for (Atom atom : atoms) {
            Sphere sphere = new Sphere(5); // Atom size
            sphere.setMaterial(new PhongMaterial(getElementColor(atom.element)));

            // Shift positions so the molecule is centered and apply scaleFactor
            sphere.setTranslateX((atom.x - centerX) * scaleFactor);
            sphere.setTranslateY(-(atom.y - centerY) * scaleFactor); // Flip Y-axis
            sphere.setTranslateZ(0); // All atoms are on the same Z-axis

            moleculeGroup.getChildren().add(sphere);
        }

        // Create cylinders for bonds
        for (int[] bond : bonds) {
            Atom atom1 = atoms.get(bond[0]);
            Atom atom2 = atoms.get(bond[1]);

            // Apply the scale factor to separate the atoms more
            double x1 = (atom1.x - centerX) * scaleFactor;
            double y1 = -(atom1.y - centerY) * scaleFactor; // Flip Y-axis

            double x2 = (atom2.x - centerX) * scaleFactor;
            double y2 = -(atom2.y - centerY) * scaleFactor; // Flip Y-axis

            // get distance using pythagors
            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            Cylinder cylinder = new Cylinder(1, distance); // bond cylinder length set
            cylinder.setMaterial(new PhongMaterial(Color.LIGHTGRAY));

            // cylinder position to middle of atoms
            cylinder.setTranslateX((x1 + x2) / 2);
            cylinder.setTranslateY((y1 + y2) / 2);
            cylinder.setTranslateZ(0);

            // calculate angle of cylinder
            double dx = x2 - x1;
            double dy = y2 - y1;
            double angleDegrees = Math.toDegrees(Math.atan2(dy, dx));

            Rotate rotate = new Rotate(angleDegrees+90, 0, 0, 0, Rotate.Z_AXIS);
            cylinder.getTransforms().add(rotate);

            moleculeGroup.getChildren().add(cylinder);
        }

        // Create cam
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-150);
        camera.setNearClip(1);
        camera.setFarClip(1000);

        // Create to display in
        SubScene subScene = new SubScene(moleculeGroup, 800, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.BLACK);
        subScene.setCamera(camera);

        // mouse control
        subScene.setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        subScene.setOnMouseDragged(e -> {
            double deltaX = e.getSceneX() - mouseX;
            double deltaY = e.getSceneY() - mouseY;

            // rotate molecule
            moleculeGroup.setRotationAxis(Rotate.Y_AXIS);
            moleculeGroup.setRotate(moleculeGroup.getRotate() - deltaX * 0.5);
            moleculeGroup.setRotationAxis(Rotate.X_AXIS);
            moleculeGroup.setRotate(moleculeGroup.getRotate() - deltaY * 0.5);

            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        subScene.setOnScroll(e -> {
            double zoomFactor = 1.1;
            if (e.getDeltaY() < 0) {
                zoomFactor = 1 / zoomFactor;
            }
            camera.setTranslateZ(camera.getTranslateZ() * zoomFactor);
        });
        
        Group root = new Group(subScene);
        Scene scene = new Scene(root, 800, 600, true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Color getElementColor(String element) {
        switch (element) {
            case "H": return Color.WHITE;
            case "C": return Color.rgb(80, 80, 80);
            case "O": return Color.RED;
            case "N": return Color.BLUE;
            case "S": return Color.YELLOW;
            case "P": return Color.rgb(255, 165, 0);
            case "F": return Color.rgb(144, 224, 144);
            case "Cl": return Color.GREEN;
            case "Br": return Color.rgb(165, 42, 42);
            case "I": return Color.rgb(148, 0, 211);
            case "Fe": return Color.rgb(255, 102, 0);
            default: return Color.GRAY;
        }
    }


    public static int getCID(String molecule) throws IOException {
        String urlStr = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + molecule + "/cids/TXT";
        String response = sendGET(urlStr);
        return response.matches("\\d+") ? Integer.parseInt(response.trim()) : -1;
    }

    public static void getMoleculeSDF(int cid) throws IOException {
        String urlStr = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/cid/" + cid + "/sdf";
        String sdfData = sendGET(urlStr);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("molecule.sdf"))) {
            writer.write(sdfData);
        }

        System.out.println("SDF file saved as molecule.sdf");
    }



    private static String sendGET(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line).append("\n");
        }
        in.close();
        return response.toString().trim();
    }
}
