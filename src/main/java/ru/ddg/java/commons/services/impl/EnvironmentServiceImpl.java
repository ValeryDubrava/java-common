package ru.ddg.java.commons.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ddg.java.commons.services.EnvironmentService;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class EnvironmentServiceImpl implements EnvironmentService {
    @Override
    public String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            log.warn("Impossible to detect localhost hostname.", e);
            return null;
        }
    }
}
