<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:exsl="http://exslt.org/common">
	<xsl:template match="/eclipsePhaseCharacter">
		<xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
		<html> 
			<head> 
				<title><xsl:value-of select="$label.title"/> - <xsl:value-of select="name"/></title> 
				
				<meta name="viewport" content="width=device-width, initial-scale=1"/> 
			
				<link rel="apple-touch-icon">
					<xsl:attribute name="href">applogo/<xsl:value-of select="game/stylesheet"/>.png</xsl:attribute>
				</link>
				<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.0-rc.1/jquery.mobile-1.3.0-rc.1.min.css" />
				<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
				<script src="http://code.jquery.com/mobile/1.3.0-rc.1/jquery.mobile-1.3.0-rc.1.min.js"></script>
				
				<style type="text/css">

.fieldValue {text-align: right;}
.rowLabel {text-align: left;}
.rowValue {text-align: center;}
				</style>

			</head> 
			<body> 
			
			<div data-role="page" id="main" data-position="fixed">
			
				<div data-role="header">
					<h1><xsl:value-of select="name"/></h1>
				</div>
			
				<div data-role="content">	
					<ul data-role="listview" data-inset="true" data-filter="false">
						<li><a href="#info" data-transition="slide"><xsl:value-of select="$label.info"/></a></li>
						<li><a href="#genScores" data-transition="slide"><xsl:value-of select="$label.genScores"/></a></li>
						<li><a href="#skills" data-transition="slide"><xsl:value-of select="$label.skills"/></a></li>
<!--    				<li><a href="#combat" data-transition="slide"><xsl:value-of select="$label.combat"/></a></li>  -->		 
						<li><a href="#psi" data-transition="slide"><xsl:value-of select="$label.psi"/></a></li>
						<li><a href="#identities" data-transition="slide"><xsl:value-of select="$label.identities"/></a></li>
						<li><a href="#muse" data-transition="slide"><xsl:value-of select="$label.muse"/></a></li>
						<li><a href="#gear" data-transition="slide"><xsl:value-of select="$label.gear"/></a></li>
						<li><a href="#morph" data-transition="slide"><xsl:value-of select="$label.morph"/></a></li>
					</ul>
				</div>
			
			</div>
			
			
			<div data-role="page" id="info" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.info"/></h1>
				</div>
			
				<div data-role="content">	
					<ul data-role="listview">
				
						<li data-role="list-divider"><xsl:value-of select="$label.background"/></li>
						<li class="fieldValue"><xsl:value-of select="background"/></li>
				
						<li data-role="list-divider"><xsl:value-of select="$label.faction"/></li>
						<li class="fieldValue"><xsl:value-of select="faction"/></li>

						<li data-role="list-divider"><xsl:value-of select="$label.morph"/></li>
						<li class="fieldValue"><xsl:value-of select="currentMorph/type"/></li>

						<li data-role="list-divider"><xsl:value-of select="$label.gender"/></li>
						<li class="fieldValue"><xsl:value-of select="gender"/></li>

						<li data-role="list-divider"><xsl:value-of select="$label.actualAge"/></li>
						<li class="fieldValue"><xsl:value-of select="actualAge/@amount"/></li>

						<li data-role="list-divider"><xsl:value-of select="$label.motivations"/></li>
						<xsl:for-each select="motivations/motivation">
							<li class="fieldValue"><xsl:value-of select="."/></li>
						</xsl:for-each>
						
						<li data-role="list-divider"><xsl:value-of select="$label.rez"/></li>
						<li class="fieldValue"><xsl:value-of select="availableExperience"/> (<xsl:value-of select="$label.totalRez"/>: <xsl:value-of select="experience"/>)</li>

					</ul>

				</div>
			
			</div>


			<div data-role="page" id="genScores" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.genScores"/></h1>
				</div>
			
				<div data-role="content">
					<ul data-role="listview">
						<li data-role="list-divider"><xsl:value-of select="$label.aptitudes"/></li>
						<li>
							<table id="aptTable" class="table-stripe table-stroke">
								<thead>
									<tr>
										<th width="100%">&#160;</th>
										<th><xsl:value-of select="$label.base"/></th>
										<th><xsl:value-of select="$label.morphBonus"/></th>
										<th><xsl:value-of select="$label.total"/></th>
									</tr>
								</thead>
								<tbody>						
									<xsl:for-each select="aptitudes/aptitude">
										<tr>
											<th class="rowLabel"><xsl:value-of select="@display"/></th>
											<td class="rowValue"><xsl:value-of select="@base"/></td>
											<td class="rowValue"><xsl:value-of select="@effectiveMorphBonus"/></td>
											<td class="rowValue"><xsl:value-of select="."/></td>
										</tr>
									</xsl:for-each>
								</tbody>
							</table>
						</li>
						<li data-role="list-divider"><xsl:value-of select="$label.scores"/></li>
						<li>
							<table id="scoreTable" class="table-stripe table-stroke">
								<tbody>											
									<tr>
										<th width="100%" class="rowLabel"><xsl:value-of select="$label.long.MOX"/></th>
										<td class="rowValue"><xsl:value-of select="moxie"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.INIT"/></th>
										<td class="rowValue"><xsl:value-of select="initiative"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.SPD"/></th>
										<td class="rowValue"><xsl:value-of select="currentMorph/speed"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.DB"/></th>
										<td class="rowValue"><xsl:value-of select="damageBonus"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.WT"/></th>
										<td class="rowValue"><xsl:value-of select="currentMorph/woundThreshold"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.DUR"/></th>
										<td class="rowValue"><xsl:value-of select="currentMorph/durability"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.DR"/></th>
										<td class="rowValue"><xsl:value-of select="currentMorph/deathRating"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.TT"/></th>
										<td class="rowValue"><xsl:value-of select="traumaThreshold"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.LUC"/></th>
										<td class="rowValue"><xsl:value-of select="lucidity"/></td>
									</tr>
									<tr>
										<th class="rowLabel"><xsl:value-of select="$label.long.IR"/></th>
										<td class="rowValue"><xsl:value-of select="insanityRating"/></td>
									</tr>
								</tbody>
							</table>
						</li>
					</ul>

				</div>
			
			</div>

			<div data-role="page" id="skills" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.skills"/></h1>
				</div>
			
				<ul data-role="listview">
					
					<xsl:call-template name="skillRows">
						<xsl:with-param name="xmlCategory" select="activeskills/skill[@category='COMBAT']"/>
						<xsl:with-param name="listHeader" select="$label.combat"/>
					</xsl:call-template>
					<xsl:call-template name="skillRows">
						<xsl:with-param name="xmlCategory" select="activeskills/skill[@category='MOVEMENT']"/>
						<xsl:with-param name="listHeader" select="$label.movement"/>
					</xsl:call-template>
					<xsl:call-template name="skillRows">
						<xsl:with-param name="xmlCategory" select="activeskills/skill[@category='MENTAL']"/>
						<xsl:with-param name="listHeader" select="$label.tech"/>
					</xsl:call-template>
					<xsl:call-template name="skillRows">
						<xsl:with-param name="xmlCategory" select="activeskills/skill[@category='SOCIAL']"/>
						<xsl:with-param name="listHeader" select="$label.social"/>
					</xsl:call-template>
					<xsl:if test="activeskills/skill[@category!='COMBAT' and @category!='SOCIAL' and @category!='MENTAL' and @category!='MOVEMENT']">
						<xsl:call-template name="skillRows">
							<xsl:with-param name="xmlCategory" select="activeskills/skill[@category!='COMBAT' and @category!='SOCIAL' and @category!='MENTAL' and @category!='MOVEMENT']"/>
							<xsl:with-param name="listHeader" select="$label.other"/>
						</xsl:call-template>
					</xsl:if>
					<xsl:call-template name="skillRows">
						<xsl:with-param name="xmlCategory" select="knowledgeskills/skill"/>
						<xsl:with-param name="listHeader" select="$label.knowledgeSkills"/>
					</xsl:call-template>
					<xsl:call-template name="skillRows">
						<xsl:with-param name="xmlCategory" select="psiskills/skill"/>
						<xsl:with-param name="listHeader" select="$label.psi"/>
					</xsl:call-template>

				</ul>
			
			</div>

			<div data-role="page" id="psi" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.psi"/></h1>
				</div>
			
				<div data-role="content">
					<ul data-role="listview">
						<xsl:call-template name="skillRows">
							<xsl:with-param name="xmlCategory" select="psiskills/skill"/>
							<xsl:with-param name="listHeader" select="$label.skills"/>
						</xsl:call-template>
						<li data-role="list-divider"><xsl:value-of select="$label.psiSleights"/></li>
						<xsl:for-each select="psiSleights/sleight">
							<li class="fieldValue"><xsl:value-of select="@display"/></li>
						</xsl:for-each>
					</ul>
				</div>
					
			</div>
			
			<div data-role="page" id="identities" data-add-back-btn="true">
				
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.identities"/></h1>
				</div>
			
				<div data-role="content">
		
					<div data-role="collapsible-set">
						<xsl:for-each select="identities/identity">
							<div data-role="collapsible">
								<xsl:if test="position() = 1">
									<xsl:attribute name="data-collapsed">false</xsl:attribute>
								</xsl:if>
								<h3><xsl:value-of select="name"/></h3>
								<ul data-role="listview">
									<li><xsl:value-of select="notes"/></li>
									<li data-role="list-divider"><xsl:value-of select="$label.reputation"/></li>
									<xsl:for-each select="reputations/reputation">
										<li><xsl:value-of select="@key"/>: <xsl:value-of select="."/></li>
									</xsl:for-each>
								</ul>
							</div>
							
						</xsl:for-each>						
					</div>
					
				</div>
				
			</div>

			<div data-role="page" id="muse" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.muse"/> (<xsl:value-of select="muse/name"/>)</h1>
				</div>
			
				<div data-role="content">
					<ul data-role="listview">
						<li data-role="list-divider"><xsl:value-of select="$label.skills"/></li>
						<li>
							<table class="table-stripe table-stroke">
								<xsl:for-each select="muse/skills/skill">
									<tr>
										<th class="rowLabel" width="100%"><xsl:value-of select="@display"/></th>
										<td class="rowValue"><xsl:value-of select="@amount"/></td>
									</tr>
								</xsl:for-each>
							</table>
						</li>				
						<li data-role="list-divider"><xsl:value-of select="$label.aptitudes"/></li>
						<li>
							<table class="table-stripe table-stroke">
								<xsl:for-each select="muse/aptitudes/aptitude">
									<tr>
										<th class="rowLabel" width="100%"><xsl:value-of select="@display"/></th>
										<td class="rowValue"><xsl:value-of select="@amount"/></td>
									</tr>
								</xsl:for-each>
							</table>
						</li>				
						<li data-role="list-divider"><xsl:value-of select="$label.scores"/></li>
						<li>
							<table class="table-stripe table-stroke">
								<tr>
									<th class="rowLabel" width="100%"><xsl:value-of select="$label.long.TT"/></th>
									<td class="rowValue"><xsl:value-of select="muse/traumaThreshold"/></td>
								</tr>
								<tr>
									<th class="rowLabel"><xsl:value-of select="$label.long.LUC"/></th>
									<td class="rowValue"><xsl:value-of select="muse/lucidity"/></td>
								</tr>
								<tr>
									<th class="rowLabel"><xsl:value-of select="$label.long.IR"/></th>
									<td class="rowValue"><xsl:value-of select="muse/insanityRating"/></td>
								</tr>
							</table>
						</li>
					</ul>				
				</div>
					
			</div>
			
			<div data-role="page" id="gear" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.gear"/></h1>
				</div>
				
				<ul data-role="listview">
					<li data-role="list-divider"><xsl:value-of select="$label.hardware"/></li>
					<xsl:for-each select="gear/item">
						<li><xsl:value-of select="."/></li>
					</xsl:for-each>
					<li data-role="list-divider"><xsl:value-of select="$label.embeddedSoftware"/></li>
					<xsl:for-each select="software/item">
						<li><xsl:value-of select="."/></li>
					</xsl:for-each>
				</ul>
			</div>
			
			<div data-role="page" id="morph" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.morph"/></h1>
				</div>
			
				<div data-role="content">
					<ul data-role="listview">
						<li data-role="list-divider"><xsl:value-of select="$label.morphType"/></li>
						<li><xsl:value-of select="currentMorph/type"/></li>
						<li data-role="list-divider"><xsl:value-of select="$label.morphStats"/></li>
						<li>
							<table class="table-stripe table-stroke">
								<tr>
									<th class="rowLabel" width="100%"><xsl:value-of select="$label.aptitudeMax"/></th>
									<td class="rowValue"><xsl:value-of select="currentMorph/aptitudeMax/@amount"/></td>
								</tr>
								<tr>
									<th class="rowLabel" width="100%"><xsl:value-of select="$label.speedMod"/></th>
									<td class="rowValue"><xsl:value-of select="currentMorph/speedModifier/@amount"/></td>
								</tr>
								<tr>
									<th class="rowLabel" width="100%"><xsl:value-of select="$label.movement"/></th>
									<td class="rowValue" style="white-space: nowrap;"><xsl:value-of select="currentMorph/movementRate"/></td>
								</tr>
							</table>				
						</li>
						<li data-role="list-divider"><xsl:value-of select="$label.morphTraits"/></li>
						<xsl:for-each select="currentMorph/traits/trait/@display">
							<li><xsl:value-of select="."/></li>
						</xsl:for-each>
						<li data-role="list-divider"><xsl:value-of select="$label.morphEnhancements"/></li>
						<xsl:for-each select="currentMorph/enhancements/enhancement">
							<li><xsl:value-of select="."/></li>
						</xsl:for-each>
					</ul>
				</div>
					
			</div>
			
			</body>
		</html>
	</xsl:template>
	
	<xsl:template name="skillRows">
		<xsl:param name="xmlCategory"/>
		<xsl:param name="listHeader"/>
		<li data-role="list-divider"><xsl:value-of select="$listHeader"/></li>
		<li>
			<table class="table-stripe table-stroke">
				<tr class="">
					<th width="100%">&#160;</th>
					<th><xsl:value-of select="$label.APT"/></th>
					<th><xsl:value-of select="$label.base"/></th>
					<th><xsl:value-of select="$label.morphBonus"/></th>
					<th><xsl:value-of select="$label.total"/></th>
					<th><xsl:value-of select="$label.specOtherBonus"/></th>
				</tr>
				<xsl:for-each select="exsl:node-set($xmlCategory)">
					<tr>
						<th class="rowLabel"><xsl:value-of select="@display"/></th>
						<td class="rowValue"><xsl:value-of select="@linkedAptitude"/></td>
						<td class="rowValue"><xsl:value-of select="@base"/></td>
						<td class="rowValue"><xsl:value-of select="@morphBonus"/></td>
						<td class="rowValue"><xsl:value-of select="@total"/></td>
						<td class="rowValue">
							<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/<xsl:text> </xsl:text></xsl:if><xsl:value-of select="."/></xsl:for-each>
						</td>
					</tr>
				</xsl:for-each>
			</table>				
		</li>
	</xsl:template>
</xsl:stylesheet>