package org.flipmed.strategies;

import org.flipmed.entities.Doctor;
import org.flipmed.entities.TimeSlot;

public interface SlotRankingStrategy {
    int compare(Doctor doctor, TimeSlot slot1, TimeSlot slot2);
}