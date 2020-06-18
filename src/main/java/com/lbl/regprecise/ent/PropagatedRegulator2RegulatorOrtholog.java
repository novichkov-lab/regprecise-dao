package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "PropagatedRegulator2RegulatorOrtholog")
public class PropagatedRegulator2RegulatorOrtholog {
	@Id
	@GeneratedValue
	@Column(name = "propagatedRegulator2RegulatorOrthologId")
    Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "propagatedRegulatorId")			
	PropagatedRegulator propagatedRegulator;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulatorId")			
	Regulator sourceRegulator;
	
	String orthologRef;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PropagatedRegulator getPropagatedRegulator() {
		return propagatedRegulator;
	}

	public void setPropagatedRegulator(PropagatedRegulator propagatedRegulator) {
		this.propagatedRegulator = propagatedRegulator;
	}

	public Regulator getSourceRegulator() {
		return sourceRegulator;
	}

	public void setSourceRegulator(Regulator sourceRegulator) {
		this.sourceRegulator = sourceRegulator;
	}

	public String getOrthologRef() {
		return orthologRef;
	}

	public void setOrthologRef(String orthologRef) {
		this.orthologRef = orthologRef;
	}
	
	
}
