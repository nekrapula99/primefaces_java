package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;



@NamedQueries({
    @NamedQuery(name = "Mopcme00.findAll", query = "SELECT m FROM Mopcme00 m WHERE m.aplopm = '03'")
    
})

@Entity
@XmlRootElement
@IdClass(Mopcme00PK.class)
public class Mopcme00 implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6370428207717965548L;
	@Id @Column private String aplopm;
	@Id @Column private String menopm;
	@Id @Column private String numopm;
	@Column private String nomopm;
	@Column private String mniopm;
	@Column private String pgiopm;
	@Column private String desopm;
	@Column private String iconpm;
	
	
//	@ManyToOne
//    @JoinColumns({
//        @JoinColumn(name="aplmen", referencedColumnName="aplopm"),
//        @JoinColumn(name="codmen", referencedColumnName="menopm")
//    })
//	private Mmenus00 mmenus00;
	
	
	
	/**
	 * Get y Set
	 */
	public String getAplopm() {
		return aplopm;
	}
	public void setAplopm(String aplopm) {
		this.aplopm = aplopm;
	}
	public String getMenopm() {
		return menopm;
	}
	public void setMenopm(String menopm) {
		this.menopm = menopm;
	}
	public String getNumopm() {
		return numopm;
	}
	public void setNumopm(String numopm) {
		this.numopm = numopm;
	}
	public String getNomopm() {
		return nomopm;
	}
	public void setNomopm(String nomopm) {
		this.nomopm = nomopm;
	}
	public String getPgiopm() {
		return pgiopm;
	}
	public void setPgiopm(String pgiopm) {
		this.pgiopm = pgiopm;
	}
	public String getDesopm() {
		return desopm;
	}
	public void setDesopm(String desopm) {
		this.desopm = desopm;
	}
	public String getMniopm() {
		return mniopm;
	}
	public void setMniopm(String mniopm) {
		this.mniopm = mniopm;
	}
	public String getIconpm() {
		return iconpm;
	}
	public void setIconpm(String iconpm) {
		this.iconpm = iconpm;
	}
	@Override
	public String toString() {
		return "Mopcme00 [aplopm=" + aplopm + ", menopm=" + menopm
				+ ", numopm=" + numopm + ", nomopm=" + nomopm + ", mniopm="
				+ mniopm + ", pgiopm=" + pgiopm + ", desopm=" + desopm
				+ ", icono=" + iconpm + "]";
	}
	
	
}
