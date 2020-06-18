package com.lbl.regprecise.ent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Regulome")
public class Regulome {
	@Id
	@GeneratedValue
	@Column(name = "regulomeId")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "genomeId")		
	private Genome genome;	
	
	private String kbaseId;
	private Date createDate;
	private String buildParams;
	private Integer sourceTypeTermId;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulome2Regulon",
			joinColumns={@JoinColumn(name="regulomeId")},
			inverseJoinColumns={@JoinColumn(name="regulonId")}
	)
	private List<Regulon> regulons = new ArrayList<Regulon>();	


	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Regulome2PropagatedRegulon",
			joinColumns={@JoinColumn(name="regulomeId")},
			inverseJoinColumns={@JoinColumn(name="propagatedRegulonId")}
	)
	private List<PropagatedRegulon> propagatedRegulons = new ArrayList<PropagatedRegulon>();	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Collection2Regulome",
			joinColumns={@JoinColumn(name="regulomeId")},
			inverseJoinColumns={@JoinColumn(name="collectionId")}
	)
	private List<Collection> collections = new ArrayList<Collection>();	

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Genome getGenome() {
		return genome;
	}

	public void setGenome(Genome genome) {
		this.genome = genome;
	}

	public String getKbaseId() {
		return kbaseId;
	}

	public void setKbaseId(String kbaseId) {
		this.kbaseId = kbaseId;
	}

	public List<Regulon> getRegulons() {
		return regulons;
	}

	public void setRegulons(List<Regulon> regulons) {
		this.regulons = regulons;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setBuildParams(String buildParams) {
		this.buildParams = buildParams;
	}

	public String getBuildParams() {
		return buildParams;
	}

	public void setSourceTypeTermId(Integer sourceTypeTermId) {
		this.sourceTypeTermId = sourceTypeTermId;
	}

	public Integer getSourceTypeTermId() {
		return sourceTypeTermId;
	}

	public void setPropagatedRegulons(List<PropagatedRegulon> propagatedRegulons) {
		this.propagatedRegulons = propagatedRegulons;
	}

	public List<PropagatedRegulon> getPropagatedRegulons() {
		return propagatedRegulons;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public List<Collection> getCollections() {
		return collections;
	}
	
}
