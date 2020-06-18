package com.lbl.regprecise.ent;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@Table(name = "Site")
public class Site extends IdObject implements Comparable<Site>{
	@Id
	@GeneratedValue
	@Column(name = "siteId")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "regulonId")		
	Regulon regulon;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "geneId")		
	Gene relativeGene;
	
	@Column(columnDefinition="Text")	
	private String sequence;
	
	Integer relativePosition;
	
	Float score;
	
	Integer moScaffoldId;
	Integer absolutePosition;
	
	Boolean isMajor;
	
	
	static Pattern pattern  = Pattern.compile("([AaTtGgCc]+)-\\((\\d+)\\)-([AaTtGgCc]+)");
	static Pattern pattern1 = Pattern.compile("([AaTtGgCc]+)-\\(\\d+\\)-([AaTtGgCc]+)");
	public static void convertToPlainSequences(Hashtable<Integer,String> siteId2SequenceHash)
	{
		// calculate average distance
		int distance = 0;
		int count = 0;
		
		for(String sequence: siteId2SequenceHash.values())
		{
			Matcher m = pattern.matcher(sequence);
			if(m.matches())
			{
				distance += Integer.parseInt(m.group(2));
				count++;
				continue;
			}
			
			m = pattern1.matcher(sequence);
			if(m.matches())
			{
				distance += Integer.parseInt(m.group(2));
				count++;
				continue;
			}			
		}
		if(count > 0) distance /= count;
		
		// calculate average distance
		
				
		// substitute sequences		
		for(Integer id : siteId2SequenceHash.keySet())
		{
			String sequence = siteId2SequenceHash.get(id);
			Matcher m = pattern.matcher(sequence);
			if(m.matches())
			{
				sequence = m.group(1) + blackString(distance) + m.group(3);
			}
			
			if(sequence.startsWith("<"))
			{
				sequence = reverseComplement(sequence.substring(1));
			}
			
			siteId2SequenceHash.put(id, sequence);
		}		
	}
	
	private static String reverseComplement(String sequence) {
		String seqPlus  = "atgcATGC";
		String seqMinus = "tacgTACG";
		
		StringBuffer sb = new StringBuffer(sequence.length());
		for(int i = sequence.length() - 1; i >= 0; i--)
		{
			char ch = sequence.charAt(i);
			int index = seqPlus.indexOf(ch);
			if(index >= 0) sb.append(seqMinus.charAt(index));
			else sb.append(ch);
		}
		return sb.toString();
	}

	private static String blackString(int distance) {
		StringBuffer sb = new StringBuffer(distance);
		for(int i = 0 ; i < distance; i++)
		{
			sb.append("-");
		}
		return sb.toString();
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Regulon getRegulon() {
		return regulon;
	}
	public void setRegulon(Regulon regulon) {
		this.regulon = regulon;
	}

	public Gene getRelativeGene() {
		return relativeGene;
	}
	public void setRelativeGene(Gene relativeGene) {
		this.relativeGene = relativeGene;
	}
	public Integer getRelativePosition() {
		return relativePosition;
	}
	public void setRelativePosition(Integer relativePosition) {
		this.relativePosition = relativePosition;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Integer getMoScaffoldId() {
		return moScaffoldId;
	}
	public void setMoScaffoldId(Integer moScaffoldId) {
		this.moScaffoldId = moScaffoldId;
	}
	public Integer getAbsolutePosition() {
		return absolutePosition;
	}
	public void setAbsolutePosition(Integer absolutePosition) {
		this.absolutePosition = absolutePosition;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getSequence() {
		if(sequence.startsWith("<")) return sequence.substring(1);		
		return sequence;
	}
	
	public String getFastaHeader()
	{
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(1);
		
		//Locus_tag (First gene name) Score=score  Pos=pos [Genome name]
	    
		return relativeGene.getLocusTag() 
			+  ((relativeGene.getName() != null && relativeGene.getName().length() > 0) ?  "(" + relativeGene.getName() + ")" :"")
			+ " Score=" +  nf.format(getScore())
			+ " Pos=" + getRelativePosition()
			+ " [" + getRegulon().getGenomeName() + "]";
/*		
		return "|id|" + id
			+ "|pos|" + relativePosition
			+ "|operon|" + relativeGene.getLocusTag();
*/					
	}

	public void setIsMajor(Boolean isMajor) {
		this.isMajor = isMajor;
	}

	public Boolean getIsMajor() {
		return isMajor;
	}

	public int compareTo(Site site) {
		return relativePosition.compareTo(site.getRelativePosition());
	}	
	
}
