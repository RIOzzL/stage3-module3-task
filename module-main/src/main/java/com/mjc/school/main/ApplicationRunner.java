package com.mjc.school.main;

import com.mjc.school.controller.annotation.processor.CommandHandlerProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ApplicationRunner {
    private final CommandHandlerProcessor handlerProcessor;

    @Autowired
    public ApplicationRunner(CommandHandlerProcessor handlerProcessor) {
        this.handlerProcessor = handlerProcessor;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        while (start) {
            handlerProcessor.invokeCommand(0);
            Integer userInput = Integer.parseInt(scanner.next());
            if (userInput < 1 || userInput > 11) {
                System.out.println(userInput + " not found! Please make correct choice!");
            } else if (userInput == 11) {
                start = false;
            } else {
                handlerProcessor.invokeCommand(userInput);
            }
        }
    }
}
