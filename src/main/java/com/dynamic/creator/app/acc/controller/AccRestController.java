package com.dynamic.creator.app.acc.controller;

import com.dynamic.creator.app.acc.model.Acc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; 

import java.util.List;

/***
 * @author wahab
 * @since v1.0
 */
@RestController
@RequestMapping("/1.0/AccRestController")
public class AccRestController {
    @Autowired 
    private AccController accController; 

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Acc getAcc(@RequestHeader(name = "REQUEST_HEADER_NAME") String sessionKey, @RequestParam(value = "id") String id) {
        return accController.getAcc(sessionKey, id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Acc> listAcc(@RequestHeader(name = "REQUEST_HEADER_NAME") String sessionKey) {
        return accController.listAcc(sessionKey);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Acc createAcc(@RequestHeader(name = "REQUEST_HEADER_NAME") String sessionKey, @RequestBody Acc acc) {
        return accController.createAcc(sessionKey, acc);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public Acc editAcc(@RequestHeader(name = "REQUEST_HEADER_NAME") String sessionKey, @RequestBody Acc acc) {
        return accController.editAcc(sessionKey, acc);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public void deleteAcc(@RequestHeader(name = "REQUEST_HEADER_NAME") String sessionKey, @RequestBody Acc acc) {
        accController.deleteAcc(sessionKey, acc);
    }
}