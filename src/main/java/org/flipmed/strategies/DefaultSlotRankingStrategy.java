package org.flipmed.strategies;

import org.flipmed.entities.Doctor;
import org.flipmed.entities.TimeSlot;

public class DefaultSlotRankingStrategy implements SlotRankingStrategy {
    public int compare(Doctor doctor, TimeSlot slot1, TimeSlot slot2) {
        return slot1.getStartTime().compareTo(slot2.getStartTime());
    }
}