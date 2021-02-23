<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:dss="http://dss.esig.europa.eu/validation/detailed-report">

	<xsl:output method="html" encoding="utf-8" indent="yes" omit-xml-declaration="yes" />

    <xsl:template match="/dss:DetailedReport">
    	<div>
    		<xsl:attribute name="class">panel panel-primary</xsl:attribute>
	   		<div>
	   			<xsl:attribute name="class">panel-heading</xsl:attribute>
	    		<xsl:attribute name="data-target">#collapseETSI</xsl:attribute>
		       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>
		       	Validation
		    </div>
		    <div>
				<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
		        <xsl:attribute name="id">collapseETSI</xsl:attribute>
		        
		    	<xsl:comment>Generated by DSS v.${project.version}</xsl:comment>
		    	
				<xsl:apply-templates select="dss:Certificate"/>
				<xsl:apply-templates select="dss:BasicBuildingBlocks[@Type='CERTIFICATE']"/>

				<xsl:apply-templates select="dss:Signature"/>
				<xsl:apply-templates select="dss:Timestamp"/>
				<xsl:apply-templates select="dss:BasicBuildingBlocks[@Type='SIGNATURE']"/>
				<xsl:apply-templates select="dss:BasicBuildingBlocks[@Type='COUNTER_SIGNATURE']"/>
				<xsl:apply-templates select="dss:BasicBuildingBlocks[@Type='TIMESTAMP']"/>
				<xsl:apply-templates select="dss:BasicBuildingBlocks[@Type='REVOCATION']"/>
				
				<xsl:apply-templates select="dss:TLAnalysis" />
			</div>
	    </div>
	    		
    </xsl:template>

	<xsl:template match="dss:Signature">
		<div>
			<xsl:attribute name="class">panel panel-primary</xsl:attribute>
			<div>
				<xsl:attribute name="class">panel-heading</xsl:attribute>
				<xsl:attribute name="data-target">#collapseSignatureValidationData<xsl:value-of select="@Id"/></xsl:attribute>
				<xsl:attribute name="data-toggle">collapse</xsl:attribute>
				
				<xsl:if test="@CounterSignature = 'true'">
					<span>
			        	<xsl:attribute name="class">label label-info pull-right</xsl:attribute>
						Counter-signature
		        	</span>
				</xsl:if>
				
				Signature <xsl:value-of select="@Id"/>
			</div>
			<xsl:if test="count(child::*[name(.)!='Conclusion']) &gt; 0">
				<div>
					<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
					<xsl:attribute name="id">collapseSignatureValidationData<xsl:value-of select="@Id"/></xsl:attribute>
					<xsl:apply-templates select="dss:ValidationProcessBasicSignature" />
					<xsl:apply-templates select="dss:Timestamp" />
					<xsl:apply-templates select="dss:ValidationProcessLongTermData" />
					<xsl:apply-templates select="dss:ValidationProcessArchivalData" />
   					
   					<xsl:apply-templates select="dss:ValidationSignatureQualification"/>
				</div>
			</xsl:if>
		</div>
	</xsl:template>
	
	<xsl:template match="dss:Timestamp">
		<div>
			<xsl:attribute name="class">panel panel-primary</xsl:attribute>
			<div>
				<xsl:attribute name="class">panel-heading</xsl:attribute>
				<xsl:attribute name="data-target">#collapseTimestamp<xsl:value-of select="@Id"/></xsl:attribute>
				<xsl:attribute name="data-toggle">collapse</xsl:attribute>
				
				Timestamp <xsl:value-of select="@Id"/>
			</div>
			<xsl:if test="count(child::*[name(.)!='Conclusion']) &gt; 0">
				<div>
					<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
					<xsl:attribute name="id">collapseTimestamp<xsl:value-of select="@Id"/></xsl:attribute>
   					<xsl:apply-templates select="dss:ValidationProcessTimestamp"/>
   					<xsl:apply-templates select="dss:ValidationTimestampQualification"/>
				</div>
			</xsl:if>
		</div>
	</xsl:template>
	
	<xsl:template match="dss:BasicBuildingBlocks">    
       <div>
       		<xsl:if test="@Id != ''">
       			<xsl:attribute name="id"><xsl:value-of select="@Id"/></xsl:attribute>
       		</xsl:if>
	   		<xsl:attribute name="class">panel panel-primary</xsl:attribute>
	   		<div>
	   			<xsl:attribute name="class">panel-heading</xsl:attribute>
	    		<xsl:attribute name="data-target">#collapseBasicBuildingBlocks<xsl:value-of select="@Id"/></xsl:attribute>
		       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>

	   			Basic Building Blocks <br/>
	   			<xsl:value-of select="@Type"/> (Id = <xsl:value-of select="@Id"/>)
	        </div>
			<xsl:if test="count(child::*[name(.)!='Conclusion']) &gt; 0">
				<xsl:variable name="PSV" select="dss:PSV" />
				<xsl:variable name="SubXCV" select="dss:XCV/dss:SubXCV" />
	    		<div>
	    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
		        	<xsl:attribute name="id">collapseBasicBuildingBlocks<xsl:value-of select="@Id"/></xsl:attribute>
					
					<xsl:apply-templates select="dss:FC" />
					<xsl:apply-templates select="dss:ISC" />
					<xsl:apply-templates select="dss:VCI" />
					<xsl:apply-templates select="dss:CV" />
					<xsl:apply-templates select="dss:SAV" />
					<xsl:apply-templates select="dss:XCV" />
    				<xsl:if test="$PSV != ''">
						<hr />
					</xsl:if>
					<xsl:apply-templates select="dss:PSV" />
					<xsl:apply-templates select="dss:PCV" />
					<xsl:apply-templates select="dss:VTS" />
    				<xsl:if test="$SubXCV != ''">
						<hr />
					</xsl:if>
					<xsl:apply-templates select="dss:XCV/dss:SubXCV" />
	    		</div>
	   		</xsl:if>
	   	</div>
    </xsl:template>

	<xsl:template match="dss:ValidationProcessBasicSignature|dss:ValidationProcessLongTermData|dss:ValidationProcessArchivalData|dss:Certificate">
		<div>
			<xsl:variable name="indicationText" select="dss:Conclusion/dss:Indication/text()"/>
	        <xsl:variable name="indicationCssClass">
	        	<xsl:choose>
					<xsl:when test="$indicationText='PASSED'">success</xsl:when>
					<xsl:when test="$indicationText='INDETERMINATE'">warning</xsl:when>
					<xsl:when test="$indicationText='FAILED'">danger</xsl:when>
					<xsl:otherwise>default</xsl:otherwise>
				</xsl:choose>
	        </xsl:variable>
			<div>
				<xsl:attribute name="class">panel panel-<xsl:value-of select="$indicationCssClass" /></xsl:attribute>
	    		<div>
					<xsl:attribute name="class">panel-heading</xsl:attribute>
					<xsl:attribute name="data-target">#collapse<xsl:value-of select="name(.)"/><xsl:value-of select="../@Id"/></xsl:attribute>
					<xsl:attribute name="data-toggle">collapse</xsl:attribute>

			       	<xsl:if test="@BestSignatureTime">
						<span>
							<xsl:attribute name="class">pull-right glyphicon glyphicon-time dss-clock</xsl:attribute>
							<xsl:attribute name="title">Best signature time : <xsl:value-of select="@BestSignatureTime"/></xsl:attribute>
		       			</span>
	       			</xsl:if>

					<xsl:if test="string-length(dss:Conclusion/dss:SubIndication) &gt; 0">
				        <span>
				        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Error) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Error"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Warning) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Warning"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:value-of select="dss:Conclusion/dss:SubIndication"/>
			        	</span>
			        </xsl:if>
			        <span>
			        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
			        	<xsl:value-of select="dss:Conclusion/dss:Indication"/>
			        </span>
			        
					<xsl:value-of select="@Title"/>
				</div>
				<xsl:if test="count(child::*[name(.)!='Conclusion']) &gt; 0">
		    		<div>
		    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
			        	<xsl:attribute name="id">collapse<xsl:value-of select="name(.)"/><xsl:value-of select="../@Id"/></xsl:attribute>
			        	<xsl:apply-templates/>
		    		</div>
		    	</xsl:if>
			</div>
		</div>
	</xsl:template>

	<xsl:template match="dss:ValidationProcessTimestamp">
    	<div>
    		<xsl:variable name="indicationText" select="dss:Conclusion/dss:Indication/text()"/>
	        <xsl:variable name="indicationCssClass">
	        	<xsl:choose>
					<xsl:when test="$indicationText='PASSED'">success</xsl:when>
					<xsl:when test="$indicationText='INDETERMINATE'">warning</xsl:when>
					<xsl:when test="$indicationText='FAILED'">danger</xsl:when>
					<xsl:otherwise>default</xsl:otherwise>
				</xsl:choose>
	        </xsl:variable>
    		<div>
    			<xsl:attribute name="class">panel panel-<xsl:value-of select="$indicationCssClass" /></xsl:attribute>
	    		<div>
	    			<xsl:attribute name="class">panel-heading</xsl:attribute>
		    		<xsl:attribute name="data-target">#collapseTimestampValidationData<xsl:value-of select="../@Id"/></xsl:attribute>
			       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>
			       
					<span>
						<xsl:attribute name="class">pull-right glyphicon glyphicon-time dss-clock</xsl:attribute>
						<xsl:attribute name="title">Production time : <xsl:value-of select="@ProductionTime"/></xsl:attribute>
	       			</span>
	       			
			       	<xsl:if test="string-length(dss:Conclusion/dss:SubIndication) &gt; 0">
				        <span>
				        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Error) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Error"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Warning) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Warning"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:value-of select="dss:Conclusion/dss:SubIndication"/>
			        	</span>
			        </xsl:if>
			        
	    			
		 			<xsl:value-of select="@Title"/> - <xsl:value-of select="@Type"/>
		        </div>
				<xsl:if test="count(child::*[name(.)!='Conclusion']) &gt; 0">
		    		<div>
		    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
			        	<xsl:attribute name="id">collapseTimestampValidationData<xsl:value-of select="../@Id"/></xsl:attribute>
			        	<xsl:apply-templates/>
		    		</div>
		    	</xsl:if>
	    	</div>
    	</div>
    </xsl:template>
    
    <xsl:template match="dss:TLAnalysis">
    	<div>
       		<xsl:if test="@Id != ''">
       			<xsl:attribute name="id"><xsl:value-of select="@Id"/></xsl:attribute>
       		</xsl:if>
    		<xsl:variable name="indicationText" select="dss:Conclusion/dss:Indication/text()"/>
	        <xsl:variable name="indicationCssClass">
	        	<xsl:choose>
					<xsl:when test="$indicationText='PASSED'">success</xsl:when>
					<xsl:when test="$indicationText='INDETERMINATE'">warning</xsl:when>
					<xsl:when test="$indicationText='FAILED'">danger</xsl:when>
					<xsl:otherwise>default</xsl:otherwise>
				</xsl:choose>
	        </xsl:variable>
    		<div>
    			<xsl:attribute name="class">panel panel-<xsl:value-of select="$indicationCssClass" /></xsl:attribute>
	    		<div>
	    			<xsl:attribute name="class">panel-heading</xsl:attribute>
		    		<xsl:attribute name="data-target">#collapseTL<xsl:value-of select="@CountryCode"/></xsl:attribute>
			       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>
		   
			       	<xsl:if test="string-length(dss:Conclusion/dss:SubIndication) &gt; 0">
				        <span>
				        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Error) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Error"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Warning) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Warning"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:value-of select="dss:Conclusion/dss:SubIndication"/>
			        	</span>
			        </xsl:if>
			       	<span>
			        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
			        	<xsl:value-of select="dss:Conclusion/dss:Indication"/>
			        </span>
		   
					<xsl:value-of select="@Title"/>
		        </div>
				<xsl:if test="count(child::*[name(.)!='Conclusion']) &gt; 0">
		    		<div>
		    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
			        	<xsl:attribute name="id">collapseTL<xsl:value-of select="@CountryCode"/></xsl:attribute>
			        	<xsl:apply-templates/>
		    		</div>
		    	</xsl:if>
	    	</div>
    	</div>
    </xsl:template>
    
    <xsl:template match="dss:ValidationSignatureQualification">
    	<div>
	    	<xsl:variable name="indicationText" select="dss:Conclusion/dss:Indication/text()"/>
	        <xsl:variable name="indicationCssClass">
	        	<xsl:choose>
					<xsl:when test="$indicationText='PASSED'">success</xsl:when>
					<xsl:when test="$indicationText='INDETERMINATE'">warning</xsl:when>
					<xsl:when test="$indicationText='FAILED'">danger</xsl:when>
					<xsl:otherwise>default</xsl:otherwise>
				</xsl:choose>
	        </xsl:variable>
    		<div>
    			<xsl:attribute name="class">panel panel-<xsl:value-of select="$indicationCssClass" /></xsl:attribute>
	    		<div>
	    			<xsl:attribute name="class">panel-heading</xsl:attribute>
		    		<xsl:attribute name="data-target">#collapseSigAnalysis<xsl:value-of select="@Id"/></xsl:attribute>
			       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>
			       	<xsl:if test="string-length(dss:Conclusion/dss:SubIndication) &gt; 0">
				        <span>
				        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Error) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Error"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Warning) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Warning"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:value-of select="dss:Conclusion/dss:SubIndication"/>
			        	</span>
			        </xsl:if>
			        
			        <span>
						<xsl:attribute name="class">pull-right</xsl:attribute>
						<xsl:value-of select="@SignatureQualification"/>	       			
	       			</span>
			        
		       		<xsl:value-of select="@Title"/>
		        </div>
	    		<div>
	    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
		        	<xsl:attribute name="id">collapseSigAnalysis<xsl:value-of select="@Id"/></xsl:attribute>
		        	<xsl:apply-templates/>
	    		</div>
    		</div>
    	</div>
    </xsl:template>
     
    <xsl:template match="dss:ValidationTimestampQualification">
    	<div>
	    	<xsl:variable name="indicationText" select="dss:Conclusion/dss:Indication/text()"/>
	        <xsl:variable name="indicationCssClass">
	        	<xsl:choose>
					<xsl:when test="$indicationText='PASSED'">success</xsl:when>
					<xsl:when test="$indicationText='INDETERMINATE'">warning</xsl:when>
					<xsl:when test="$indicationText='FAILED'">danger</xsl:when>
					<xsl:otherwise>default</xsl:otherwise>
				</xsl:choose>
	        </xsl:variable>
    		<div>
    			<xsl:attribute name="class">panel panel-<xsl:value-of select="$indicationCssClass" /></xsl:attribute>
	    		<div>
	    			<xsl:attribute name="class">panel-heading</xsl:attribute>
		    		<xsl:attribute name="data-target">#collapseTstAnalysis<xsl:value-of select="@Id"/></xsl:attribute>
			       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>
			       	<xsl:if test="string-length(dss:Conclusion/dss:SubIndication) &gt; 0">
				        <span>
				        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Error) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Error"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Warning) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Warning"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:value-of select="dss:Conclusion/dss:SubIndication"/>
			        	</span>
			        </xsl:if>
			        
			        <span>
						<xsl:attribute name="class">pull-right</xsl:attribute>
						<xsl:value-of select="@TimestampQualification"/>	       			
	       			</span>
			        
		       		<xsl:value-of select="@Title"/>
		        </div>
	    		<div>
	    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
		        	<xsl:attribute name="id">collapseTstAnalysis<xsl:value-of select="@Id"/></xsl:attribute>
		        	<xsl:apply-templates/>
	    		</div>
    		</div>
    	</div>
    </xsl:template>
    
    <xsl:template match="dss:ValidationCertificateQualification">
    	<div>
	    	<xsl:variable name="indicationText" select="dss:Conclusion/dss:Indication/text()"/>
	        <xsl:variable name="indicationCssClass">
	        	<xsl:choose>
					<xsl:when test="$indicationText='PASSED'">success</xsl:when>
					<xsl:when test="$indicationText='INDETERMINATE'">warning</xsl:when>
					<xsl:when test="$indicationText='FAILED'">danger</xsl:when>
					<xsl:otherwise>default</xsl:otherwise>
				</xsl:choose>
	        </xsl:variable>
    		<div>
    			<xsl:attribute name="class">panel panel-<xsl:value-of select="$indicationCssClass" /></xsl:attribute>
    			<xsl:attribute name="style">margin-top : 10px</xsl:attribute>
	    		<div>
	    			<xsl:attribute name="class">panel-heading</xsl:attribute>
		    		<xsl:attribute name="data-target">#cert-qual-<xsl:value-of select="generate-id(.)"/></xsl:attribute>
			       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>
			       	<xsl:if test="string-length(dss:Conclusion/dss:SubIndication) &gt; 0">
				        <span>
				        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Error) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Error"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Warning) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Warning"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:value-of select="dss:Conclusion/dss:SubIndication"/>
			        	</span>
			        </xsl:if>
			        
			        <span>
						<xsl:attribute name="class">pull-right glyphicon glyphicon-time dss-clock</xsl:attribute>
						<xsl:attribute name="title"><xsl:value-of select="@DateTime"/></xsl:attribute>
	       			</span>
			        <span>
						<xsl:attribute name="class">pull-right</xsl:attribute>
						<xsl:value-of select="@CertificateQualification"/>	       			
	       			</span>
			        
		       		<xsl:value-of select="@Title"/>
		 			<xsl:if test="@Id">
		       			<br />    
			        	<xsl:value-of select="concat('Id = ', @Id)"/>
		        	</xsl:if>
		        </div>
	    		<div>
	    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
		        	<xsl:attribute name="id">cert-qual-<xsl:value-of select="generate-id(.)"/></xsl:attribute>
		        	<xsl:apply-templates/>
	    		</div>
    		</div>
    	</div>
    </xsl:template>

    <xsl:template name="signature-conclusion">
        <xsl:param name="Conclusion"/>
        
        <xsl:variable name="indicationText" select="$Conclusion/dss:Indication"/>
        <xsl:variable name="indicationCssClass">
        	<xsl:choose>
				<xsl:when test="$indicationText='PASSED'">label-success</xsl:when>
				<xsl:when test="$indicationText='INDETERMINATE'">label-warning</xsl:when>
				<xsl:when test="$indicationText='FAILED'">label-danger</xsl:when>
			</xsl:choose>
        </xsl:variable>
        
        <span>
        	<xsl:attribute name="class">label <xsl:value-of select="$indicationCssClass" /></xsl:attribute>
            <xsl:value-of select="$Conclusion/dss:Indication"/>
        </span>
        
        <xsl:if test="string-length($Conclusion/dss:SubIndication) &gt; 0">
			<xsl:text> </xsl:text>
	        <span>
	        	<xsl:attribute name="class">label <xsl:value-of select="$indicationCssClass" /></xsl:attribute>
	        	<xsl:if test="string-length($Conclusion/dss:Error) &gt; 0">
	        		<xsl:attribute name="title"><xsl:value-of select="$Conclusion/dss:Error"/></xsl:attribute>
	        	</xsl:if>
	        	<xsl:value-of select="$Conclusion/dss:SubIndication"/>
        	</span>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="dss:FC|dss:ISC|dss:VCI|dss:CV|dss:SAV|dss:XCV|dss:PSV|dss:PCV|dss:VTS">
		<div>
       		<xsl:attribute name="id"><xsl:value-of select="../@Id"/>-<xsl:value-of select="name()"/></xsl:attribute>
			<xsl:attribute name="class">row</xsl:attribute>
			<xsl:attribute name="style">margin-bottom:5px;margin-top:5px;</xsl:attribute>
			<div>
				<xsl:attribute name="class">col-md-8</xsl:attribute>
				<strong>
					<xsl:value-of select="@Title"/> :
				</strong>
			</div>
			<div>
				<xsl:attribute name="class">col-md-4</xsl:attribute>
				<xsl:call-template name="signature-conclusion">
					<xsl:with-param name="Conclusion" select="dss:Conclusion" />
				</xsl:call-template>
			</div>
		</div>
		<xsl:apply-templates select="dss:Constraint" />
    </xsl:template>

	<xsl:template match="dss:SubXCV|dss:RAC|dss:RFC">
    	<div>
    		<xsl:variable name="indicationText" select="dss:Conclusion/dss:Indication/text()"/>
	        <xsl:variable name="indicationCssClass">
	        	<xsl:choose>
					<xsl:when test="$indicationText='PASSED'">success</xsl:when>
					<xsl:when test="$indicationText='INDETERMINATE'">warning</xsl:when>
					<xsl:when test="$indicationText='FAILED'">danger</xsl:when>
					<xsl:otherwise>default</xsl:otherwise>
				</xsl:choose>
	        </xsl:variable>
	        <xsl:variable name="parentId">
	        	<xsl:choose>
					<xsl:when test="name()='SubXCV'" ><xsl:value-of select="../../@Id"/></xsl:when>
					<xsl:otherwise><xsl:value-of select="concat(../@Id, '-', ../../../@Id)"/></xsl:otherwise>
	        	</xsl:choose>
	        </xsl:variable>
    		<xsl:variable name="currentId" select="concat(name(), '-', @Id, '-', $parentId)"/>
       		<xsl:attribute name="id"><xsl:value-of select="$currentId"/></xsl:attribute>
    		<div>
    			<xsl:attribute name="class">panel panel-<xsl:value-of select="$indicationCssClass" /></xsl:attribute>
    			<xsl:attribute name="style">margin-top : 10px</xsl:attribute>
	    		<div>
	    			<xsl:attribute name="class">panel-heading</xsl:attribute>
		    		<xsl:attribute name="data-target">#collapse-SubXCV-<xsl:value-of select="$currentId"/></xsl:attribute>
			       	<xsl:attribute name="data-toggle">collapse</xsl:attribute>
			       	
		       		<xsl:if test="@TrustAnchor = 'true'">
		       			<span>
							<xsl:attribute name="class">glyphicon glyphicon-thumbs-up pull-right</xsl:attribute>
							<xsl:attribute name="style">font-size : 20px; margin-left : 5px;</xsl:attribute>
							<xsl:attribute name="title">Trust Anchor</xsl:attribute>		       			
		       			</span>
		       		</xsl:if>
		   
					<xsl:if test="string-length(dss:Conclusion/dss:SubIndication) &gt; 0">
				        <span>
				        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Error) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Error"/></xsl:attribute>
				        	</xsl:if>
				        	<xsl:if test="string-length(dss:Conclusion/dss:Warning) &gt; 0">
				        		<xsl:attribute name="title"><xsl:value-of select="dss:Conclusion/dss:Warning"/></xsl:attribute>
				        	</xsl:if>
							<xsl:attribute name="style">margin-left : 5px;</xsl:attribute>
				        	<xsl:value-of select="dss:Conclusion/dss:SubIndication"/>
			        	</span>
			        </xsl:if>
			       	<span>
			        	<xsl:attribute name="class">label label-<xsl:value-of select="$indicationCssClass" /> pull-right</xsl:attribute>
						<xsl:attribute name="style">margin-left : 5px;</xsl:attribute>
			        	<xsl:value-of select="dss:Conclusion/dss:Indication"/>
			        </span>
					
    				<xsl:if test="@SelfSigned = 'true'">
		       			<span>
							<xsl:attribute name="class">glyphicon glyphicon-user pull-right</xsl:attribute>
							<xsl:attribute name="style">font-size : 20px; margin-left : 5px;</xsl:attribute>
							<xsl:attribute name="title">Self-signed</xsl:attribute>		       			
		       			</span>
	       			</xsl:if>
					
    				<xsl:if test="dss:CrossCertificate">
		       			<span>
							<xsl:attribute name="class">glyphicon glyphicon-link pull-right</xsl:attribute>
							<xsl:attribute name="style">font-size : 20px; margin-left : 5px;</xsl:attribute>
							<xsl:attribute name="title">Cross-Certification: <xsl:value-of select="dss:CrossCertificate"/></xsl:attribute>		       			
		       			</span>
	       			</xsl:if>
					
    				<xsl:if test="dss:EquivalentCertificate">
		       			<span>
							<xsl:attribute name="class">glyphicon glyphicon-refresh pull-right</xsl:attribute>
							<xsl:attribute name="style">font-size : 20px; margin-left : 5px;</xsl:attribute>
							<xsl:attribute name="title">Equivalent certification: <xsl:value-of select="dss:EquivalentCertificate"/></xsl:attribute>		       			
		       			</span>
	       			</xsl:if>
			       	
		        	<xsl:value-of select="concat(@Title, ' Id = ', @Id)"/>
	    			
		        </div>
		        
		       	<xsl:if test="name() != 'SubXCV' or @TrustAnchor != 'true'">
		    		<div>
		    			<xsl:attribute name="class">panel-body collapse in show</xsl:attribute>
			        	<xsl:attribute name="id">collapse-SubXCV-<xsl:value-of select="$currentId"/></xsl:attribute>
			        	<xsl:apply-templates/>
		    		</div>
	    		</xsl:if>
    		</div>
    	</div>
    </xsl:template>

    <xsl:template match="dss:Constraint">
	    <div>
	    	<xsl:attribute name="class">row</xsl:attribute>
	    	<div>
	    		<xsl:attribute name="class">col-md-8</xsl:attribute>
				<xsl:value-of select="dss:Name"/>
	    		<xsl:if test="@Id">
					<xsl:variable name="BlockType" select="@BlockType"/>
	    			<a>
						<xsl:choose>
							<xsl:when test="$BlockType='SUB_XCV'">
								<xsl:attribute name="href">#SubXCV-<xsl:value-of select="concat(@Id, '-', ../../@Id)"/></xsl:attribute>
							</xsl:when>
							<xsl:when test="$BlockType='RAC' and name(..) != 'RAC'">
								<xsl:attribute name="href">#RAC-<xsl:value-of select="concat(@Id, '-', ../@Id, '-', ../../../@Id)"/></xsl:attribute>
							</xsl:when>
							<xsl:when test="$BlockType='RFC'">
								<xsl:attribute name="href">#RFC-<xsl:value-of select="concat(@Id, '-', ../@Id, '-', ../../../@Id)"/></xsl:attribute>
							</xsl:when>
							<xsl:when test="$BlockType='PSV'">
								<xsl:attribute name="href">#<xsl:value-of select="@Id"/>-PSV</xsl:attribute>
							</xsl:when>
							<xsl:when test="$BlockType='PCV'">
								<xsl:attribute name="href">#<xsl:value-of select="@Id"/>-PCV</xsl:attribute>
							</xsl:when>
							<xsl:when test="$BlockType='VTS'">
								<xsl:attribute name="href">#<xsl:value-of select="@Id"/>-VTS</xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="href">#<xsl:value-of select="@Id"/></xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:attribute name="title">Details</xsl:attribute>
						<xsl:attribute name="style">margin-left : 10px</xsl:attribute>
						<span>
							<xsl:attribute name="class">glyphicon glyphicon-circle-arrow-right</xsl:attribute>
						</span>
					</a>
	    		</xsl:if>
	    	</div>
	    	<div>
	    		<xsl:attribute name="class">col-md-4</xsl:attribute>
	        	<xsl:variable name="statusText" select="dss:Status"/>
	        	<xsl:choose>
					<xsl:when test="$statusText='OK'">
						<span>
							<xsl:attribute name="class">glyphicon glyphicon-ok-sign text-success</xsl:attribute>
							<xsl:attribute name="title">OK</xsl:attribute>
						</span>
					</xsl:when>
					<xsl:when test="$statusText='NOT OK'">
						<span>
							<xsl:attribute name="class">glyphicon glyphicon-remove-sign text-danger</xsl:attribute>
							<xsl:attribute name="title"><xsl:value-of select="dss:Error" /></xsl:attribute>
						</span>
					</xsl:when>
					<xsl:when test="$statusText='WARNING'">
						<span>
							<xsl:attribute name="class">glyphicon glyphicon-exclamation-sign text-warning</xsl:attribute>
							<xsl:attribute name="title"><xsl:value-of select="dss:Warning" /></xsl:attribute>
						</span>
					</xsl:when>
					<xsl:when test="$statusText='INFORMATION'">
						<span>
							<xsl:attribute name="class">glyphicon glyphicon-info-sign text-info</xsl:attribute>
							<xsl:attribute name="title"><xsl:value-of select="dss:Info" /></xsl:attribute>
						</span>
					</xsl:when>
					<xsl:otherwise>
						<span>
							<xsl:value-of select="dss:Status" />
						</span>
					</xsl:otherwise>
	    		</xsl:choose>
	    		
	    		<xsl:if test="dss:AdditionalInfo">
		    		<span>
		    			<xsl:attribute name="class">glyphicon glyphicon-plus-sign text-info</xsl:attribute>
						<xsl:attribute name="style">margin-left : 10px</xsl:attribute>
						<xsl:attribute name="data-toggle">tooltip</xsl:attribute>
						<xsl:attribute name="data-placement">right</xsl:attribute>
						<xsl:attribute name="title"><xsl:value-of select="dss:AdditionalInfo" /></xsl:attribute>
		    		</span>
	    		</xsl:if>
	    	</div>
	    </div>
        <xsl:apply-templates select="dss:Info"/>
    </xsl:template>

    <xsl:template match="dss:Constraint/dss:Info"/>

	<xsl:template match="dss:Info|dss:Warning|dss:Error">
		<div>
			<xsl:attribute name="class">row</xsl:attribute>
			<div>
				<xsl:attribute name="class">col-md-6</xsl:attribute>
				<xsl:value-of select="name(@*[not(name()='NameId')][1])" />
			</div>
			<div>
				<xsl:attribute name="class">col-md-6</xsl:attribute>
				<xsl:value-of select="@*[not(name()='NameId')]" />
				<xsl:text> </xsl:text>
				<xsl:apply-templates />
			</div>
		</div>
	</xsl:template>
  
	<xsl:template match="*">
		<xsl:comment>
			Ignored tag:
			<xsl:value-of select="name()" />
		</xsl:comment>
	</xsl:template>

</xsl:stylesheet>
