package com.pra.controller;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.SimpleBaseDataController;
import com.pra.model.PartyIn;
import com.pra.view.PartyInDataWindow;

@Service
public class PartyInDataController extends SimpleBaseDataController<PartyIn, PartyInController, PartyInDataWindow> {

}
