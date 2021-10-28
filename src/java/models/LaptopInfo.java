/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author HP
 */
public class LaptopInfo {
    private int id;
    private String cpu;
    private String ram;
    private String screen;
    private String graphic;
    private String hardDrive;
    private String weigh;
    private String origin;
    private int debutYear;

    public LaptopInfo() {
    }

    public LaptopInfo(String cpu, String ram, String screen, String graphic, String hardDrive, String weigh, String origin, int debutYear) {
        this.cpu = cpu;
        this.ram = ram;
        this.screen = screen;
        this.graphic = graphic;
        this.hardDrive = hardDrive;
        this.weigh = weigh;
        this.origin = origin;
        this.debutYear = debutYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public String getWeigh() {
        return weigh;
    }

    public void setWeigh(String weigh) {
        this.weigh = weigh;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }
    
    
}
