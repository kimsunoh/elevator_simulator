package middle;

import java.util.*;

/**
 * Created by ksh on 2014-05-24.
 */
/*
사용자가 누른 층을 순서대로 저장하는 클래스
 */
public class InputBuffer {
    private static InputBuffer instance = null;
    Map<Integer, String> floorSelectionsState;
    List<Integer> floorSelectionStateInElevator;

    private InputBuffer() {
        floorSelectionsState = Collections.synchronizedMap(new HashMap<Integer, String>());
        floorSelectionStateInElevator = Collections.synchronizedList(new ArrayList<Integer>());
    }

    public static InputBuffer getInstance() {
        if (instanceIsEmpty()) {
            synchronized (InputBuffer.class) {
                if (instanceIsEmpty()) {
                    instance = new InputBuffer();
                }
            }
        }
        return instance;
    }

    private static boolean instanceIsEmpty() {
        return instance == null;
    }

    public boolean selectUpDown(Integer selectionFloor, String upDown) {

        if (floorSelectionsState.containsKey(selectionFloor)) {
            if (floorSelectionsState.containsValue(upDown)) {
                return false;
            }
        }
        floorSelectionsState.put(selectionFloor, upDown);
        return true;
    }

    public boolean selectFloorInElevator(Integer targetFloor) {
        if (floorSelectionStateInElevator.contains(targetFloor)) {
            return false;
        }
        floorSelectionStateInElevator.add(targetFloor);
        return true;
    }

    public List<Integer> getAllSelectionFloorInElevator() {
        return floorSelectionStateInElevator;
    }

    public void removeSelectionFloorInElevator(Integer floor) {
        if (floorSelectionStateInElevator.contains(floor)) {
            floorSelectionStateInElevator.remove(floor);
        }
    }

    public void removeSelectionFloor(Integer floor) {
        if (floorSelectionsState.containsKey(floor)) {
            floorSelectionsState.remove(floor);
        }
    }

    public ArrayList<Integer> getAllSelectionFloorOutside() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator itor = floorSelectionsState.keySet().iterator();
        while (itor.hasNext()) {
            Integer temp = Integer.parseInt(itor.next().toString());
            result.add(temp);
        }
        return result;
    }

    public ArrayList<Integer> getAllSelectionFloorOutside(String upDown) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator itor = floorSelectionsState.keySet().iterator();
        while (itor.hasNext()) {
            Integer temp = Integer.parseInt(itor.next().toString());
            String str =floorSelectionsState.get(temp);
            if(upDown.equals(str)){
                result.add(temp);
            }
        }
        return result;
    }

    public Map<Integer, String> getAllSelectionFloorHashMap() {
        return floorSelectionsState;
    }
}
