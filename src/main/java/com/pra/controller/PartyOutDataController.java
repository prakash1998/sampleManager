package com.pra.controller;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.SimpleBaseDataController;
import com.pra.model.PartyOut;
import com.pra.view.PartyOutDataWindow;

@Service
public class PartyOutDataController extends SimpleBaseDataController<PartyOut, PartyOutController, PartyOutDataWindow> {

}
