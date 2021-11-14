package Package;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Simulation extends Pane {

    public static List<Car> cars = new LinkedList<>();

    public static List<Light> lights = new LinkedList<>();

    public static List<Light> stopLights = new LinkedList<>();

    public List<NormalRoad> getAllRoads() {
        return allRoads;
    }

    public List<Crossroad> getAllCrossroads() {
        return allCrossroads;
    }

    protected List<NormalRoad> allRoads = new ArrayList<>();
    protected List<Crossroad> allCrossroads = new ArrayList<>();

    public static List<CarGeneratorField> allCarGenerators= new ArrayList<>();

    public static List<CarGeneratorField> getAllCarGenerators() {
        return allCarGenerators;
    }

    public static void setAllCarGenerators(List<CarGeneratorField> allCarGenerators) {
        Simulation.allCarGenerators = allCarGenerators;
    }

    public Simulation() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("resources/grass.png"));

        BackgroundImage myBI= new BackgroundImage(new Image(new FileInputStream("resources/grass.png")),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

        RoadGenerator road=new RoadGenerator();
        road.generate(0,1000,100, false, this,allRoads);
        road.generate(0,1000,300, true, this,allRoads);
        road.generate(0,1000,300, false, this,allRoads);
        road.generate(0,1000,500, true, this,allRoads);
//        System.out.println("to tu");
        for (CarGeneratorField generator:allCarGenerators){
//            System.out.println(generator.getPositionX()+" "+generator.getPositionY()+" "+generator.getDirection());
        }
//        System.out.println(allCarGenerators.size());
        CrossroadGenerator crossroads= new CrossroadGenerator();
        crossroads.checkAndGenerate(allRoads, allCrossroads, this);
        RoadsPrinter printroads=new RoadsPrinter();
        printroads.printRoads(allRoads,allCrossroads,this);

    }

    public void start() {


        SimLoop simLoop = new SimLoop();
        simLoop.start();
    }
}
