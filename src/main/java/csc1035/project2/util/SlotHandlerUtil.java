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

    /**
     * Set a new time range
     * @param newStartTime the starting time
     * @param newEndTime the ending time
     */
    private static void setTimeRange(LocalTime newStartTime, LocalTime newEndTime) {
        startTime = newStartTime;
        endTime = newEndTime;
    }

    /**
     * Set new number of slot and intervals
     * @param newSlotNum the new slot number
     * @param newInterval the new interval(in minutes)
     */
    private static void setSlotInterval(int newSlotNum, int newInterval) {
        slots = newSlotNum;
        interval = newInterval;
    }

    /**
     * Create time points based on the number of slots and the interval set
     * @return a list of LocalTime object
     */
    public static List<LocalTime> generateTimesForSlots() {
        List<LocalTime> ltList = new ArrayList<>();
        for (int i = 0; i < slots+1; i++) {
            ltList.add(startTime.plusMinutes(i * interval));
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

    /**
     * Gives a time range based on the slotNum given
     * @param slotNum the slot needed for the range
     * @return LocalTime array of length 2 where the elements
     * are the starting and ending time respectively
     */
    public static LocalTime[] slotToTimeRange(int slotNum) {
        List<LocalTime> localTimes = generateTimesForSlots();
        return new LocalTime[] {localTimes.get(slotNum-1), localTimes.get(slotNum)};
    }

}
