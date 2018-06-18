package com.dynamic.creator.app.acc.controller;

import com.dynamic.creator.app.acc.dao.AccRepo;
import com.dynamic.creator.app.acc.model.Acc;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/***
 * @author wahab
 * @since v1.0
 */
@Controller
public class AccController {
    @Autowired
    private AccRepo accRepo;

    public Acc getAcc(String sessionKey, String id) {
        return accRepo.findOne(id);
    }

    public List<Acc> listAcc(String sessionKey) {
        return Lists.newArrayList(accRepo.findAll());
    }

    public Acc createAcc(String sessionKey, Acc acc) {
        return accRepo.save(acc);
    }

    public Acc editAcc(String sessionKey, Acc acc) {
        return accRepo.save(acc);
    }

    public void deleteAcc(String sessionKey, Acc acc) {
        accRepo.delete(acc);
    }
}