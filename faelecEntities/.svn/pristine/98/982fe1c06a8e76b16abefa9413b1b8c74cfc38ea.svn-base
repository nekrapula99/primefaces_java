package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;




@NamedQueries({
    @NamedQuery(name = "Mmenus00.findAll", query = "SELECT m FROM Mmenus00 m WHERE m.aplmen = :aplmen and m.indmen = 'S' "),
    @NamedQuery(name = "Factura.findByNommen", query = "SELECT m FROM Mmenus00 m WHERE m.nommen = :nommen")
})

@Entity
@XmlRootElement
@IdClass(Mmenus00PK.class)
public class Mmenus00 implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7203279451589929782L;
	@Id @Column private String aplmen;	
	@Id @Column private String codmen;	
	@Column private String nommen;
	@Column private String indmen; 
	@Column private String iconen;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumns({
        @JoinColumn(name="aplopm", referencedColumnName="aplmen"),
        @JoinColumn(name="menopm", referencedColumnName="codmen")
    })
    private List<Mopcme00> listMopcme00;
	
	//bi-directional many-to-one association to Mtabla00
	@OneToMany(mappedBy="mmenus00")
	private List<Maccgr00> maccgr00s;
	
	
	
	/*
	 * Get y Set
	 */
	public String getAplmen() {
		return aplmen;
	}
	public void setAplmen(String aplmen) {
		this.aplmen = aplmen;
	}
	public String getCodmen() {
		return codmen;
	}
	public void setCodmen(String codmen) {
		this.codmen = codmen;
	}
	public String getNommen() {
		return nommen;
	}
	public void setNommen(String nommen) {
		this.nommen = nommen;
	}
	public String getIndmen() {
		return indmen;
	}
	public void setIndmen(String indmen) {
		this.indmen = indmen;
	}
	public List<Mopcme00> getListMopcme00() {
		return listMopcme00;
	}
	public void setListMopcme00(List<Mopcme00> listMopcme00) {
		this.listMopcme00 = listMopcme00;
	}
	@Override
	public String toString() {
		return "Mmenus00 [aplmen=" + aplmen + ", codmen=" + codmen
				+ ", nommen=" + nommen + ", indmen=" + indmen
				+ ", listMopcme00=" + listMopcme00 + "]";
	}
	public List<Maccgr00> getMaccgr00s() {
		return maccgr00s;
	}
	public void setMaccgr00s(List<Maccgr00> maccgr00s) {
		this.maccgr00s = maccgr00s;
	}
	public String getIconen() {
		return iconen;
	}
	public void setIconen(String iconen) {
		this.iconen = iconen;
	}

}
