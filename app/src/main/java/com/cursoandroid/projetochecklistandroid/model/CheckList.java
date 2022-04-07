package com.cursoandroid.projetochecklistandroid.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CheckList implements Serializable {
    private Integer id;
    private String saida_retorno;
    private String data;
    private String hora;
    private String placa;
    private String motorista;
    private Integer km;
    private String tracao;
    private String calibragem_pneu;
    private String estepe;
    private String freio_dianteiro;
    private String freio_traseiro;
    private String balanceamento;
    private String limpeza_radiador;
    private String oleo_motor;
    private String filtro_oleo;
    private String parachoque_dianteiro;
    private String parachoque_traseiro;
    private String placas_caminhao;
    private String cinto_seguranca;
    private String pedais;
    private String abertura_portas;

    public CheckList(String saidaRetorno, String data, String hora, String placa,
                     String motorista, Integer km, String tracao, String calibragem_pneu,
                     String estepe, String freio_dianteiro, String freio_traseiro,
                     String balanceamento, String limpeza_radiador, String oleo_motor,
                     String filtro_oleo, String parachoque_dianteiro, String parachoque_traseiro,
                     String placas_caminhao, String cinto_seguranca, String pedais,
                     String abertura_portas) {
        this.saida_retorno = saidaRetorno;
        this.data = data;
        this.hora = hora;
        this.placa = placa;
        this.motorista = motorista;
        this.km = km;
        this.tracao = tracao;
        this.calibragem_pneu = calibragem_pneu;
        this.estepe = estepe;
        this.freio_dianteiro = freio_dianteiro;
        this.freio_traseiro = freio_traseiro;
        this.balanceamento = balanceamento;
        this.limpeza_radiador = limpeza_radiador;
        this.oleo_motor = oleo_motor;
        this.filtro_oleo = filtro_oleo;
        this.parachoque_dianteiro = parachoque_dianteiro;
        this.parachoque_traseiro = parachoque_traseiro;
        this.placas_caminhao = placas_caminhao;
        this.cinto_seguranca = cinto_seguranca;
        this.pedais = pedais;
        this.abertura_portas = abertura_portas;
    }

    public CheckList() {

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
        return saida_retorno;
    }

    public String getTracao() {
        return tracao;
    }

    public String getCalibragemPneu() {
        return calibragem_pneu;
    }

    public String getEstepe() {
        return estepe;
    }

    public String getFreioDianteiro() {
        return freio_dianteiro;
    }

    public String getFreioTraseiro() {
        return freio_traseiro;
    }

    public String getBalanceamento() {
        return balanceamento;
    }

    public String getLimpezaRadiador() {
        return limpeza_radiador;
    }

    public String getOleoMotor() {
        return oleo_motor;
    }

    public String getFiltroOleo() {
        return filtro_oleo;
    }

    public String getParaChoqueDianteiro() {
        return parachoque_dianteiro;
    }

    public String getParaChoqueTraseiro() {
        return parachoque_traseiro;
    }

    public String getPlacasCaminhao() {
        return placas_caminhao;
    }

    public String getCintoSeguranca() {
        return cinto_seguranca;
    }

    public String getPedais() {
        return pedais;
    }

    public String getAberturaPortas() {
        return abertura_portas;
    }

    @NonNull
    @Override
    public String toString() {
        return "CheckList{" +
                "id=" + id +
                ", saida_retorno='" + saida_retorno + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", placa='" + placa + '\'' +
                ", motorista='" + motorista + '\'' +
                ", km=" + km +
                ", tracao='" + tracao + '\'' +
                ", calibragem_pneu='" + calibragem_pneu + '\'' +
                ", estepe='" + estepe + '\'' +
                ", freio_dianteiro='" + freio_dianteiro + '\'' +
                ", freio_traseiro='" + freio_traseiro + '\'' +
                ", balanceamento='" + balanceamento + '\'' +
                ", limpeza_radiador='" + limpeza_radiador + '\'' +
                ", oleo_motor='" + oleo_motor + '\'' +
                ", filtro_oleo='" + filtro_oleo + '\'' +
                ", parachoque_dianteiro='" + parachoque_dianteiro + '\'' +
                ", parachoque_traseiro='" + parachoque_traseiro + '\'' +
                ", placas_caminhao='" + placas_caminhao + '\'' +
                ", cinto_seguranca='" + cinto_seguranca + '\'' +
                ", pedais='" + pedais + '\'' +
                ", abertura_portas='" + abertura_portas + '\'' +
                '}';
    }
}
