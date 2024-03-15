package logic;

import java.util.HashMap;
import java.util.Map;

public class customerDelivery {
    private String name;
    private String address;
    private Map<String, Map<String, Integer>> distances;

    public customerDelivery() {
        this.name = "";
        this.address = "";
        this.distances = new HashMap<>();

        distances.put("St.Peter", new HashMap<String, Integer>() {{
            put("St.Peter", 0);
            put("St.John", 300);
            put("Lanao", 150);
            put("Maguindanao", 200);
        }});

        distances.put("St.John", new HashMap<String, Integer>() {{
            put("St.Peter", 150);
            put("St.John", 0);
            put("Lanao", 200);
            put("Maguindanao", 300);
        }});

        distances.put("Lanao", new HashMap<String, Integer>() {{
            put("St.Peter", 100);
            put("St.John", 120);
            put("Lanao", 0);
            put("Maguindanao", 200);
        }});

        distances.put("Maguindanao", new HashMap<String, Integer>() {{
            put("St.Peter", 200);
            put("St.John", 200);
            put("Lanao", 100);
            put("Maguindanao", 0);
        }});
    }

    // ... rest of your code ...

    public int getDistance(String from, String to) {
        return distances.get(from).get(to);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Map<String, Integer>> getDistances() {
        return distances;
    }
}