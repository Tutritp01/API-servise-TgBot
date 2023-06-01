package com.tutrit.persistence.core.service;

import com.tutrit.persistence.core.bean.Engineer;

@Deprecated
public interface EngineerService {
    Engineer saveEngineer(Engineer engineer);

    Engineer getEngineer(String id);
}
