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

    private int slots = 10;
    private int interval = 60;
    private final Random random;
    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(18, 0, 0);


    public SlotHandlerUtil(int slots, int interval) {
        this.slots = slots;
        this.interval = interval;
        this.random = new Random();
    }

    public SlotHandlerUtil(int slots, int interval, Random random) {
        this.slots = slots;
        this.interval = interval;
        this.random = random;
    }

    public SlotHandlerUtil(int slots, int interval, Random random, LocalTime startTime, LocalTime endTime) {
        this.slots = slots;
        this.interval = interval;
        this.random = random;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
