package com.example.DST.assembler;

import com.example.DST.controller.VoucherController;
import com.example.DST.entity.VoucherEntity;
import com.example.DST.model.VoucherModel;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class VoucherModelAssembler extends RepresentationModelAssemblerSupport<VoucherEntity, VoucherModel> {
    public VoucherModelAssembler() {super(VoucherController.class, VoucherModel.class);}
    @Override
    public VoucherModel toModel(VoucherEntity entity) {
        VoucherModel model = new VoucherModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
