package test_data.computer;

public class ComputerData {
    private String processorType;
    private String ram;
    private String hdd;
    private String os;
    private String software;

    public ComputerData(String processorType, String ram, String hdd, String os, String software) {
        this.processorType = processorType;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.software = software;
    }

    public String getProcessorType() {
        return processorType;
    }

    public String getRam() {
        return ram;
    }

    public String getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getSoftware() {
        return software;
    }
}
