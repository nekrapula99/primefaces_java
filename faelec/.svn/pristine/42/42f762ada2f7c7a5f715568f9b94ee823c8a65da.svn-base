package com.tlm.faelec.faces.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.tlm.faelec.utils.Utils;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		try {
			if(!"".equals(String.valueOf(value))){
				boolean correoOk = Utils.validatePattern(String.valueOf(value), Utils.paramsRb.getString("pattern_valida_correo"));
				
				if(!correoOk){
					throw new ValidatorException(new FacesMessage(""));
				}
			}
				
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage(""));
		}
	}

}
