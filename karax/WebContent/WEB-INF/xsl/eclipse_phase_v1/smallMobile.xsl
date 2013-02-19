<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/eclipsePhaseCharacter">
		<xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
		<html> 
			<head> 
				<title><xsl:value-of select="$label.title"/> - <xsl:value-of select="name"/></title> 
				
				<meta name="viewport" content="width=device-width, initial-scale=1"/> 
			
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
						<li><a href="#askCombat" data-transition="slide"><xsl:value-of select="$label.askCombat"/></a></li>
						<li><a href="#askMouv" data-transition="slide"><xsl:value-of select="$label.askMouv"/></a></li>
						<li><a href="#askTech" data-transition="slide"><xsl:value-of select="$label.askTech"/></a></li>
						<li><a href="#askSoc" data-transition="slide"><xsl:value-of select="$label.askSoc"/></a></li>
						<li><a href="#askOther" data-transition="slide"><xsl:value-of select="$label.askOther"/></a></li>
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
					</ul>

				</div>
			
			</div>


			<div data-role="page" id="genScores" data-add-back-btn="true">
			
				<div data-role="header" data-position="fixed">
					<h1><xsl:value-of select="$label.genScores"/></h1>
				</div>
			
				<div data-role="content">	
					
					<table id="aptTable" class="table-stripe">
						<thead>
							<tr>
								<th width="100%"><xsl:value-of select="$label.aptitudes"/></th>
								<th><xsl:value-of select="$label.base"/></th>
								<th><xsl:value-of select="$label.morph"/></th>
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

					<table id="aptTable" class="table-stripe">
						<thead>
							<tr>
								<th width="100%"><xsl:value-of select="$label.scores"/></th>
								<th>&#160;</th>
							</tr>
						</thead>
						<tbody>											
							<tr>
								<th class="rowLabel"><xsl:value-of select="$label.long.MOX"/></th>
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

				</div>
			
			</div>

			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>