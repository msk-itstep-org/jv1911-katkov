package org.itstep.msk.app.service;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface ValidationMessagesService {

    void createValidationMessages(BindingResult bindingResult, Map<String, List<String>> errors);
}
