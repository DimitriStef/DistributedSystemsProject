class Value {
    private Bus bus;
    private double latitude, longitude;

    public Value(Bus bus, double lat, double longi) {
        this.bus = bus;
        latitude = lat;
        longitude = longi;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus b) {
        bus = b;
    }

    public double getLat() {
        return latitude;
    }

    public double getLongi() {
        return longitude;
    }

    public void setLat(double latitude) {
        this.latitude = latitude;
    }

    public void setLongi(double longitude) {
        this.longitude = longitude;
    }
}