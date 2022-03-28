package com.cursoandroid.projetochecklistandroid.model;

import java.io.Serializable;

public class CheckList implements Serializable {
    private Integer id;
    private final String saidaRetorno;
    private final String data;
    private final String hora;
    private final String placa;
    private final String motorista;
    private final Integer km;
    private final String tracao;
    private final String calibragemPneu;
    private final String estepe;
    private final String freioDianteiro;
    private final String freioTraseiro;
    private final String balanceamento;
    private final String limpezaRadiador;
    private final String oleoMotor;
    private final String filtroOleo;
    private final String paraChoqueDianteiro;
    private final String paraChoqueTraseiro;
    private final String placasCaminhao;
    private final String cintoSeguranca;
    private final String pedais;
    private final String aberturaPortas;

    public CheckList(String saidaRetorno, String data, String hora, String placa,
                     String motorista, Integer km, String tracao, String calibragemPneu,
                     String estepe, String freioDianteiro, String freioTraseiro,
                     String balanceamento, String limpezaRadiador, String oleoMotor,
                     String filtroOleo, String paraChoqueDianteiro, String paraChoqueTraseiro,
                     String placasCaminhao, String cintoSeguranca, String pedais,
                     String aberturaPortas) {
        this.saidaRetorno = saidaRetorno;
        this.data = data;
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

    public String getData() {
        return data;
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

    public Integer getKm() {
        return km;
    }

    public Integer getId() {
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
