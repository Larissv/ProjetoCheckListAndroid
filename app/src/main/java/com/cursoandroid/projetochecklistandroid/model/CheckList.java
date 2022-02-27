package com.cursoandroid.projetochecklistandroid.model;

import java.io.Serializable;

public class CheckList implements Serializable {
    private int id;
    private String saidaRetorno;
    private String dataC;
    private String hora;
    private String placa;
    private String motorista;
    private String km;
    private String tracao;
    private String calibragemPneu;
    private String estepe;
    private String freioDianteiro;
    private String freioTraseiro;
    private String balanceamento;
    private String limpezaRadiador;
    private String oleoMotor;
    private String filtroOleo;
    private String paraChoqueDianteiro;
    private String paraChoqueTraseiro;
    private String placasCaminhao;
    private String cintoSeguranca;
    private String pedais;
    private String aberturaPortas;

    public CheckList() {
    }

    public CheckList(String saidaRetorno, String dataC, String hora, String placa,
                     String motorista, String km, String tracao, String calibragemPneu,
                     String estepe, String freioDianteiro, String freioTraseiro,
                     String balanceamento, String limpezaRadiador, String oleoMotor,
                     String filtroOleo, String paraChoqueDianteiro, String paraChoqueTraseiro,
                     String placasCaminhao, String cintoSeguranca, String pedais,
                     String aberturaPortas) {
        this.saidaRetorno = saidaRetorno;
        this.dataC = dataC;
        this.hora = hora;
        this.placa = placa;
        this.motorista = motorista;
        this.km = km;
        this.tracao = tracao;
        this.calibragemPneu = calibragemPneu;
        this.estepe = estepe;
        this.freioDianteiro = freioDianteiro;
        this.freioTraseiro = freioTraseiro;
        this.balanceamento = balanceamento;
        this.limpezaRadiador = limpezaRadiador;
        this.oleoMotor = oleoMotor;
        this.filtroOleo = filtroOleo;
        this.paraChoqueDianteiro = paraChoqueDianteiro;
        this.paraChoqueTraseiro = paraChoqueTraseiro;
        this.placasCaminhao = placasCaminhao;
        this.cintoSeguranca = cintoSeguranca;
        this.pedais = pedais;
        this.aberturaPortas = aberturaPortas;
    }

    public String getDataC() {
        return dataC;
    }

    public String getHora() {
        return hora;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMotorista() {
        return motorista;
    }

    public String getKm() {
        return km;
    }

    public int getId() {
        return id;
    }

    public String getSaidaRetorno() {
        return saidaRetorno;
    }

    public String getTracao() {
        return tracao;
    }

    public String getCalibragemPneu() {
        return calibragemPneu;
    }

    public String getEstepe() {
        return estepe;
    }

    public String getFreioDianteiro() {
        return freioDianteiro;
    }

    public String getFreioTraseiro() {
        return freioTraseiro;
    }

    public String getBalanceamento() {
        return balanceamento;
    }

    public String getLimpezaRadiador() {
        return limpezaRadiador;
    }

    public String getOleoMotor() {
        return oleoMotor;
    }

    public String getFiltroOleo() {
        return filtroOleo;
    }

    public String getParaChoqueDianteiro() {
        return paraChoqueDianteiro;
    }

    public String getParaChoqueTraseiro() {
        return paraChoqueTraseiro;
    }

    public String getPlacasCaminhao() {
        return placasCaminhao;
    }

    public String getCintoSeguranca() {
        return cintoSeguranca;
    }

    public String getPedais() {
        return pedais;
    }

    public String getAberturaPortas() {
        return aberturaPortas;
    }
}
