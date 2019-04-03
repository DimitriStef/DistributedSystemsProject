class Bus {
    private String lineNumber, routeCode, vehicleId, lineName, busLineId, info;

    public Bus(String lineNumber, String routeCode, String vehicleId, String lineName, String busLineId, String info) {
        this.lineNumber = lineNumber;
        this.routeCode = routeCode;
        this.vehicleId = vehicleId;
        this.lineName = lineName;
        this.busLineId = busLineId;
        this.info = info;
    }

    public Bus() {

    }

    public String getLineNum() {
        return lineNumber;
    }

    public void setLineNum(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getVId() {
        return vehicleId;
    }

    public void setVId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getlineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String busLineId() {
        return busLineId;
    }

    public void setBusLineId(String busLineId) {
        this.busLineId = busLineId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}