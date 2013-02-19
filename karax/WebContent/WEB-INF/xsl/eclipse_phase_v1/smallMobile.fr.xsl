<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:import href="smallMobile.xsl"/>
  <xsl:output method="html" encoding="UTF-8"/>
  
  <xsl:variable name="label.title" select="'Fiche de Personnage'"/>
 
  <xsl:variable name="label.info" select="'Infos générales'"/>
  <xsl:variable name="label.genScores" select="'Aptitudes et Dérivés'"/>
  <xsl:variable name="label.skills" select="'Compétences'"/>
  <xsl:variable name="label.combat" select="'Combat'"/>
  <xsl:variable name="label.tech" select="'Techniques'"/>
  <xsl:variable name="label.social" select="'Sociales'"/>
  <xsl:variable name="label.other" select="'Autres'"/>
  <xsl:variable name="label.psi" select="'Psi'"/>
  <xsl:variable name="label.APT" select="'APT'"/>
  <xsl:variable name="label.identities" select="'Identités'"/>
  <xsl:variable name="label.muse" select="'Muse'"/>
  <xsl:variable name="label.gear" select="'Équipement'"/>
  <xsl:variable name="label.morph" select="'Morphe'"/>

  <xsl:variable name="label.background" select="'Historique'"/>
  <xsl:variable name="label.faction" select="'Faction'"/>
  <xsl:variable name="label.gender" select="'Genre'"/>
  <xsl:variable name="label.actualAge" select="'Âge Effectif'"/>
  
  <!-- Isolate locale-specific content -->
  <xsl:variable name="label.aptitudes" select="'Aptitudes'"/>
  <xsl:variable name="label.COG" select="'COG'"/>
  <xsl:variable name="label.COO" select="'COO'"/>
  <xsl:variable name="label.INT" select="'INT'"/>
  <xsl:variable name="label.SOM" select="'SOM'"/>
  <xsl:variable name="label.WIL" select="'VOL'"/>
  <xsl:variable name="label.REF" select="'REF'"/>
  <xsl:variable name="label.SAV" select="'AST'"/>
  <xsl:variable name="label.long.COG" select="'COGnition'"/>
  <xsl:variable name="label.long.COO" select="'COOrdination'"/>
  <xsl:variable name="label.long.INT" select="'INTuition'"/>
  <xsl:variable name="label.long.SOM" select="'SOMatique'"/>
  <xsl:variable name="label.long.WIL" select="'VOLonté'"/>
  <xsl:variable name="label.long.REF" select="'RÉFlexes'"/>
  <xsl:variable name="label.long.SAV" select="'ASTuce'"/>
  <xsl:variable name="label.scores" select="'Scores Dérivés'"/>
  <xsl:variable name="label.base" select="'B'"/>
  <xsl:variable name="label.morphBonus" select="'M'"/>
  <xsl:variable name="label.total" select="'Total'"/>
  <xsl:variable name="label.long.MOX" select="'AUDace'"/>
  <xsl:variable name="label.long.INIT" select="'INITiative'"/>
  <xsl:variable name="label.long.SPD" select="'RAPidité'"/>
  <xsl:variable name="label.long.DB" select="'Bonus de Dommages'"/>
  <xsl:variable name="label.long.WT" select="'Seuil de Blessure'"/>
  <xsl:variable name="label.long.DUR" select="'ENDurance'"/>
  <xsl:variable name="label.long.DR" select="'Seuil de Mort'"/>
  <xsl:variable name="label.long.TT" select="'Seuil de Trauma'"/>
  <xsl:variable name="label.long.LUC" select="'LUCidité'"/>
  <xsl:variable name="label.long.IR" select="'Seuil d´Aliénation'"/>
  <xsl:variable name="label.morphType" select="'Type'"/>
  <xsl:variable name="label.morphStats" select="'Statistiques'"/>
  <xsl:variable name="label.aptitudeMax" select="'Limite d´Aptitude'"/>
  <xsl:variable name="label.speedMod" select="'Bonus de Rapidité'"/>
  <xsl:variable name="label.movement" select="'Mouvement'"/>
  <xsl:variable name="label.rez" select="'Expérience'"/>
  <xsl:variable name="label.totalRez" select="'Total'"/>
  <xsl:variable name="label.damage" select="'Dégâts'"/>
  <xsl:variable name="label.wound" select="'Bless.'"/>
  <xsl:variable name="label.stress" select="'Stress'"/>
  <xsl:variable name="label.trauma" select="'Traumas'"/>
  <xsl:variable name="label.traits" select="'Traits d´Ego'"/>
  <xsl:variable name="label.motivations" select="'Motivations'"/>
  <xsl:variable name="label.activeSkills" select="'Compétences Actives'"/>
  <xsl:variable name="label.knowledgeSkills" select="'Connaissances'"/>
  <xsl:variable name="label.linkedAptitude" select="'Aptitude'"/>
  <xsl:variable name="label.specOtherBonus" select="'Notes'"/>
  <xsl:variable name="label.reputation" select="'Réputation'"/>
  <xsl:variable name="label.MUSE" select="'MUSE'"/>
  <xsl:variable name="label.backupNotes" select="'Sauvegarde'"/>
  <xsl:variable name="label.contacts" select="'Contacts'"/>
  <xsl:variable name="label.ID" select="'ID'"/>
  <xsl:variable name="label.REPA" select="'@-Rep'"/>
  <xsl:variable name="label.REPC" select="'C-Rep'"/>
  <xsl:variable name="label.REPE" select="'E-Rep'"/>
  <xsl:variable name="label.REPF" select="'F-Rep'"/>
  <xsl:variable name="label.REPG" select="'G-Rep'"/>
  <xsl:variable name="label.REPI" select="'I-Rep'"/>
  <xsl:variable name="label.REPR" select="'R-Rep'"/>
  <xsl:variable name="label.psiSleights" select="'Passes Psi'"/>
  <xsl:variable name="label.armor" select="'Armure'"/>
  <xsl:variable name="label.energyArmor" select="'Énergie'"/>
  <xsl:variable name="label.kineticArmor" select="'Cinétique'"/>
  <xsl:variable name="label.meleeWeapon" select="'Arme de Mêlée'"/>
  <xsl:variable name="label.rangedWeapon" select="'Arme à Distance'"/>
  <xsl:variable name="label.weaponSkill" select="'Comp.'"/>
  <xsl:variable name="label.weaponDV" select="'VD'"/>
  <xsl:variable name="label.weaponAP" select="'PA'"/>
  <xsl:variable name="label.weaponRange" select="'Portée'"/>
  <xsl:variable name="label.rangeShort" select="'C'"/>
  <xsl:variable name="label.rangeMedium" select="'M'"/>
  <xsl:variable name="label.rangeLong" select="'L'"/>
  <xsl:variable name="label.rangeExtreme" select="'E'"/>
  <xsl:variable name="label.SS" select="'CC'"/>
  <xsl:variable name="label.SA" select="'SA'"/>
  <xsl:variable name="label.BF" select="'TR'"/>
  <xsl:variable name="label.FA" select="'FA'"/>
  <xsl:variable name="label.morphTraits" select="'Traits de Morphe'"/>
  <xsl:variable name="label.morphEnhancements" select="'Implants, Améliorations et Customisations'"/>
  <xsl:variable name="label.hardware" select="'Matériel'"/>
  <xsl:variable name="label.embeddedSoftware" select="'Logiciels Embarqués'"/>
</xsl:stylesheet>