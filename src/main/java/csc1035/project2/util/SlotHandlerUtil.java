package csc1035.project2.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * SlotHandler handles the logic for the slot
 * logic for the booking system.
 */
public class SlotHandlerUtil {

    private static int slots = 10;
    private static int interval = 60;
    private static LocalTime startTime = LocalTime.of(8, 0, 0);
    private static LocalTime endTime = LocalTime.of(18, 0, 0);

    private static final Random random = new Random();

    private static void setTimeRange(LocalTime newStartTime, LocalTime newEndTime) {
        startTime = newStartTime;
        endTime = newEndTime;
    }

    private static void setSlotInterval(int newSlotNum, int newInterval) {
        slots = newSlotNum;
        interval = newInterval;
    }

    public static List<LocalTime> generateTimesForSlots() {
        List<LocalTime> ltList = new ArrayList<>();
        for (int i = 0; i < slots+1; i++) {
            ltList.add(startTime.plusMinutes(i * interval));
        }

        LocalTime[] time;
        for (int i = 1; i < slots+1; i++) {
            time = slotToTimeRange(ltList, i);
        }
        return ltList;
    }

    public static Integer[] generateSlotsArr(int n) {
        Integer[] result = new Integer[n];
        for (int i = 1; i < n+1; i++) {
            result[i-1] = i;
        }
        return result;
    }

    public static LocalTime[] slotToTimeRange(List<LocalTime> localTimes, int slotNum) {
        return new LocalTime[] {localTimes.get(slotNum-1), localTimes.get(slotNum)};
    }

}
