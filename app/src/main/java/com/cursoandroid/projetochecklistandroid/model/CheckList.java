package com.cursoandroid.projetochecklistandroid.model;

import java.io.Serializable;

public class CheckList implements Serializable {
    private int id;
    private int saidaRetorno;
    private String dataC;
    private String hora;
    private String placa;
    private String motorista;
    private String km;
    private int tracao;
    private int calibragemPneu;
    private int estepe;
    private int freioDianteiro;
    private int freioTraseiro;
    private int balanceamento;
    private int limpezaRadiador;
    private int oleoMotor;
    private int filtroOleo;
    private int paraChoqueDianteiro;
    private int paraChoqueTraseiro;
    private int placasCaminhao;
    private int cintoSeguranca;
    private int pedais;
    private int aberturaPortas;

    public CheckList() {
    }

    public CheckList(int checkedRadioButtonId, String toString, String fontFeatureSettings,
                     String toString1, String toString2, String toString3,
                     int checkedRadioButtonId1, int checkedRadioButtonId2,
                     int checkedRadioButtonId3, int checkedRadioButtonId4,
                     int checkedRadioButtonId5, int checkedRadioButtonId6,
                     int checkedRadioButtonId7, int checkedRadioButtonId8,
                     int checkedRadioButtonId9, int checkedRadioButtonId10,
                     int checkedRadioButtonId11, int checkedRadioButtonId12,
                     int checkedRadioButtonId13, int checkedRadioButtonId14, int checkedRadioButtonId15) {

        this.saidaRetorno = checkedRadioButtonId;
        this.dataC = toString;
        this.hora = fontFeatureSettings;
        this.placa = toString1;
        this.motorista = toString2;
        this.km = toString3;
        this.tracao = checkedRadioButtonId1;
        this.calibragemPneu = checkedRadioButtonId2;
        this.estepe = checkedRadioButtonId3;
        this.freioDianteiro = checkedRadioButtonId4;
        this.freioTraseiro = checkedRadioButtonId5;
        this.balanceamento = checkedRadioButtonId6;
        this.limpezaRadiador = checkedRadioButtonId7;
        this.oleoMotor = checkedRadioButtonId8;
        this.filtroOleo = checkedRadioButtonId9;
        this.paraChoqueDianteiro = checkedRadioButtonId10;
        this.paraChoqueTraseiro = checkedRadioButtonId11;
        this.placasCaminhao = checkedRadioButtonId12;
        this.cintoSeguranca = checkedRadioButtonId13;
        this.pedais = checkedRadioButtonId14;
        this.aberturaPortas = checkedRadioButtonId15;
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

    @Override
    public String toString() {
        return "CheckList{" +
                "id=" + id +
                ", saidaRetorno=" + saidaRetorno +
                ", dataC='" + dataC + '\'' +
                ", hora='" + hora + '\'' +
                ", placa='" + placa + '\'' +
                ", motorista='" + motorista + '\'' +
                ", km='" + km + '\'' +
                ", tracao=" + tracao +
                ", calibragemPneu=" + calibragemPneu +
                ", estepe=" + estepe +
                ", freioDianteiro=" + freioDianteiro +
                ", freioTraseiro=" + freioTraseiro +
                ", balanceamento=" + balanceamento +
                ", limpezaRadiador=" + limpezaRadiador +
                ", oleoMotor=" + oleoMotor +
                ", filtroOleo=" + filtroOleo +
                ", paraChoqueDianteiro=" + paraChoqueDianteiro +
                ", paraChoqueTraseiro=" + paraChoqueTraseiro +
                ", placasCaminhao=" + placasCaminhao +
                ", cintoSeguranca=" + cintoSeguranca +
                ", pedais=" + pedais +
                ", aberturaPortas=" + aberturaPortas +
                '}';
    }
}
