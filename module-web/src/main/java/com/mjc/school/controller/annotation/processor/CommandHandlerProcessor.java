package com.mjc.school.controller.annotation.processor;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.operation.MenuCommands;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CommandHandlerProcessor {

    @Autowired
    MenuCommands menu;


    @SneakyThrows
    public void invokeCommand(int chosenOperationNumber) {
        Method[] declaredMethods = MenuCommands.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(CommandHandler.class)) {
                CommandHandler annotation = declaredMethod.getAnnotation(CommandHandler.class);
                if (annotation.value() == chosenOperationNumber) {
                    declaredMethod.invoke(menu);
                }
            }
        }
    }
}
