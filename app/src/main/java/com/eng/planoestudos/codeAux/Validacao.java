package com.eng.planoestudos.codeAux;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 29/11/2018.
 */

public class Validacao {


    public boolean ValidaEmail (String email) {
        boolean status = false;
        //Validar email APENAS COM @, FIREBASE USA ASSIM
        String emailRegex = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if ( matcher.matches() ){
            status = true;
        }

        return status;
    }

    public  boolean ValidaNome (String nome) {
        boolean status = false;

        String nomeRegex  = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){1,}$";

        Pattern pattern = Pattern.compile(nomeRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nome);

        if ( matcher.matches() ){
            status = true;
        }

        return status;
    }

    public boolean ValidaSenha (String senha1, String senha2) {
        boolean status = true;

        // Senha deve ser maior que 6 caracteres
        if( ( senha1.equals("") ) || ( senha1.length()< 6 ) || ( senha2.equals("") ) || ( senha2.length()< 6 ) ){
            status = false;
        }
        if( !senha1.equals(senha2)){
            status = false;
        }

        return status;
    }

}
