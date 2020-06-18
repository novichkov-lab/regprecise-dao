package com.lbl.regprecise.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pavel Novichkov
 *
 */
@Entity
@Table(name = "Term")
public class Term extends IdObject{
	public final static int TERM_GENOME = 1;
	public final static int TERM_GENE = 2;
	public final static int TERM_OPERON = 3;
	public final static int TERM_REGULATOR = 4;
	public final static int TERM_REG_INTERACTION = 5;
	public static final int TERM_SITE = 6;
	public static final int TERM_LOCUS = 7;
	public static final int TERM_REGULON = 8;
	
	
	
	public static final int COLLECTION_TYPE_PRIVATE = 100 + 0;
	public static final int COLLECTION_TYPE_BY_REGULATOR = 100 + 1;
	public static final int COLLECTION_TYPE_BY_TAXONOMY = 100 + 2;
	public static final int COLLECTION_TYPE_BY_PATHWAY = 100 + 3;
	public static final int COLLECTION_TYPE_BY_REGFAM = 100 + 4;		
	public static final int COLLECTION_TYPE_PROPAGATION = 100 + 5;		
	public static final int COLLECTION_TYPE_STUDENT_PROJECT = 100 + 6;		
	public static final int COLLECTION_TYPE_BY_EFFECTOR = 100 + 7;
	

	public static final int TERM_REGULATION_TYPE_TF = 201;
	public static final int TERM_REGULATION_TYPE_RNA = 202;
	
	public static final int TERM_REGULOG_STATE_DEPRECATED = -1;
	public static final int TERM_REGULOG_STATE_UNDEF = 0;
	public static final int TERM_REGULOG_STATE_UNDER_ANNOTATION = 301;
	public static final int TERM_REGULOG_STATE_FINISHED = 302;
	public static final int TERM_REGULOG_STATE_APPROVED = 303;
	public static final int TERM_REGULOG_STATE_PUBLIC = 304;
	
	public static final int TERM_COLLECTION_STATE_PRIVATE = 303;
	public static final int TERM_COLLECTION_STATE_PUBLIC = 304;	
	
	public static final int TERM_PROFILETYPE_PALINDROME = 400 + 1; 
	public static final int TERM_PROFILETYPE_SINGLE_BOX = 400 + 2; 
	public static final int TERM_PROFILETYPE_DIRECT_REPEAT = 400 + 3; 
	public static final int TERM_PROFILETYPE_GENERIC_REPEAT = 400 + 4; 
	public static final int TERM_PROFILETYPE_INVERTED_REPEAT = 400 + 5; 
	public static final int TERM_PROFILETYPE_UNDEF = 400 + 6; 
	public static final int TERM_PROFILETYPE_MULTI_BOX = 400 + 7; 
	public static final int TERM_PROFILETYPE_MEME_PALINDROME = 400 + 8; 
	public static final int TERM_PROFILETYPE_MEME_GENERIC_MOTIF = 400 + 9; 
	
	public static final int LOG_JOB_PROPAGATE_REGULOGS = 1;
	public static final int LOG_JOB_GENERATE_LOGO = 511;
	
	
	public static final int LOG_ERROR_CANNOT_PROPAGATE_TO_GENOME = 1;
	public static final int LOG_ERROR_CANNOT_PROPAGATE_PGP_COLLECTION = 2;
	public static final int LOG_ERROR_CANNOT_PROPAGATE_REGULOG = 3;	
	public static final int LOG_ERROR_CANNOT_GENERATE_LOGO = 4;

	public static final int LOG_MSG_REGULOG_PROPAGATED = 1001;
	public static final Integer LOG_MSG_GENOME_PROPAGATED = 1002;
	public static final Integer LOG_MSG_COLLECTION_PROPAGATED = 1003;
	
	
	
	@Id
	@GeneratedValue
	@Column(name="termId")
	Integer id;
	String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
