package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "PropagatedRegulator")
public class PropagatedRegulator extends IdObject{

	@Id
	@GeneratedValue
	@Column(name = "propagatedRegulatorId")
	private Integer id;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "propagatedRegulonId")			
    private PropagatedRegulon propagatedRegulon;
	
	private Integer moLocusId;
    private String moLocusTag;
    private Integer moLocusGI;
    private String moFunction;
    private String kbaseId;
    
	
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})	
	@JoinColumns({
		@JoinColumn(name = "propagatedRegulatorId")
	})	
    private List<PropagatedRegulator2RegulatorOrtholog> orthologousRegulators = new ArrayList<PropagatedRegulator2RegulatorOrtholog>();
		

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PropagatedRegulon getPropagatedRegulon() {
		return propagatedRegulon;
	}

	public void setPropagatedRegulon(PropagatedRegulon propagatedRegulon) {
		this.propagatedRegulon = propagatedRegulon;
	}

	public Integer getMoLocusId() {
		return moLocusId;
	}

	public void setMoLocusId(Integer moLocusId) {
		this.moLocusId = moLocusId;
	}

	public String getMoLocusTag() {
		return moLocusTag;
	}

	public void setMoLocusTag(String moLocusTag) {
		this.moLocusTag = moLocusTag;
	}

	public Integer getMoLocusGI() {
		return moLocusGI;
	}

	public void setMoLocusGI(Integer moLocusGI) {
		this.moLocusGI = moLocusGI;
	}

	public void setOrthologousRegulators(List<PropagatedRegulator2RegulatorOrtholog> orthologousRegulators) {
		this.orthologousRegulators = orthologousRegulators;
	}

	public List<PropagatedRegulator2RegulatorOrtholog> getOrthologousRegulators() {
		return orthologousRegulators;
	}

	public void setMoFunction(String moFunction) {
		this.moFunction = moFunction;
	}

	public String getMoFunction() {
		return moFunction;
	}

	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}

	public String getKbaseId() {
		return kbaseId;
	}
}
