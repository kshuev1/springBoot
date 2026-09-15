package ru.netology.springBootDemo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryConsumer {

    private final List<byte[]> memory = new ArrayList<>();

    public void consumeMemory() {
        memory.add(new byte[10 * 1024 * 1024]);
    }

    public int getAllocatedBlocks() {
        return memory.size();
    }
}