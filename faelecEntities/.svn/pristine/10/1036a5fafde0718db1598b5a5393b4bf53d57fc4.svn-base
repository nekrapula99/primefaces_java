package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MUSUAR00 database table.
 * 
 */
@Entity
@Table(name="MUSUAR00")
@NamedQuery(name="Musuar00.findAll", query="SELECT m FROM Musuar00 m")
public class Musuar00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Musuar00PK id;

	@Column(name="ADMDEC")
	private String admdec;

	@Column(name="ADMDIN")
	private String admdin;

	@Column(name="ADMPVJ")
	private String admpvj;

	@Column(name="ADMSEU")
	private String admseu;

	@Column(name="ADMSIS")
	private String admsis;

	@Column(name="ADMSIU")
	private String admsiu;

	@Column(name="AREUSU")
	private String areusu;

	@Column(name="CDFUSU")
	private String cdfusu;

	@Column(name="CIAUSU")
	private String ciausu;

	@Column(name="CONESU")
	private Timestamp conesu;

	@Column(name="COTUSU")
	private String cotusu;

	@Column(name="GRUUSU")
	private String gruusu;

	@Column(name="INAPSU")
	private String inapsu;

	@Column(name="INDUSU")
	private String indusu;

	@Column(name="NOMUSU")
	private String nomusu;

	@Column(name="UACTSU")
	private String uactsu;
	
	// Variables utilizadas en el login
	@Transient private boolean usuarioValido;
	@Transient private boolean asesorValido;
	@Transient Mgente00 mgente00;
	
	public Musuar00() {
	}

	public Musuar00PK getId() {
		return this.id;
	}

	public void setId(Musuar00PK id) {
		this.id = id;
	}

	public String getAdmdec() {
		return this.admdec;
	}

	public void setAdmdec(String admdec) {
		this.admdec = admdec;
	}

	public String getAdmdin() {
		return this.admdin;
	}

	public void setAdmdin(String admdin) {
		this.admdin = admdin;
	}

	public String getAdmpvj() {
		return this.admpvj;
	}

	public void setAdmpvj(String admpvj) {
		this.admpvj = admpvj;
	}

	public String getAdmseu() {
		return this.admseu;
	}

	public void setAdmseu(String admseu) {
		this.admseu = admseu;
	}

	public String getAdmsis() {
		return this.admsis;
	}

	public void setAdmsis(String admsis) {
		this.admsis = admsis;
	}

	public String getAdmsiu() {
		return this.admsiu;
	}

	public void setAdmsiu(String admsiu) {
		this.admsiu = admsiu;
	}

	public String getAreusu() {
		return this.areusu;
	}

	public void setAreusu(String areusu) {
		this.areusu = areusu;
	}

	public String getCdfusu() {
		return this.cdfusu;
	}

	public void setCdfusu(String cdfusu) {
		this.cdfusu = cdfusu;
	}

	public String getCiausu() {
		return this.ciausu;
	}

	public void setCiausu(String ciausu) {
		this.ciausu = ciausu;
	}

	public Timestamp getConesu() {
		return this.conesu;
	}

	public void setConesu(Timestamp conesu) {
		this.conesu = conesu;
	}

	public String getCotusu() {
		return this.cotusu;
	}

	public void setCotusu(String cotusu) {
		this.cotusu = cotusu;
	}

	public String getGruusu() {
		return this.gruusu;
	}

	public void setGruusu(String gruusu) {
		this.gruusu = gruusu;
	}

	public String getInapsu() {
		return this.inapsu;
	}

	public void setInapsu(String inapsu) {
		this.inapsu = inapsu;
	}

	public String getIndusu() {
		return this.indusu;
	}

	public void setIndusu(String indusu) {
		this.indusu = indusu;
	}

	public String getNomusu() {
		return this.nomusu;
	}

	public void setNomusu(String nomusu) {
		this.nomusu = nomusu;
	}

	public String getUactsu() {
		return this.uactsu;
	}

	public void setUactsu(String uactsu) {
		this.uactsu = uactsu;
	}

	public boolean getUsuarioValido() {
		return usuarioValido;
	}

	public void setUsuarioValido(boolean usuarioValido) {
		this.usuarioValido = usuarioValido;
	}

	public Mgente00 getMgente00() {
		return mgente00;
	}

	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}

	public boolean getAsesorValido() {
		return asesorValido;
	}

	public void setAsesorValido(boolean asesorValido) {
		this.asesorValido = asesorValido;
	}



}