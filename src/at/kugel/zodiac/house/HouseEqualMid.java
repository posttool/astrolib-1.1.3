package at.kugel.zodiac.house;

import at.kugel.zodiac.util.CalcUtil;
/**
   EqualMid Hausberechnung: This house system is just like the Equal system
   except that we start our 12 equal segments from the Midheaven instead of
   the Ascendant.
   @see at.kugel.zodiac.house.HouseBasic
   @author Kugel, <i>Theossos Comp Group</i>
   @version 1.00 - 17062000 finished.
   @version 1.01 - 24062000 problems with latitude fixed.
   @version 1.10 - 02022002 ok for JDK 1.0 and 1.1; no changes
   @since JDK 1.0
*/
public final class HouseEqualMid extends HouseBasic {

   /** Validity range of the house system. */
   private final double[] subRange = {-CalcUtil.RFromD(90.0-rAxis),CalcUtil.PIH};

   /** Berechnet H&auml;user in Radiant. Verwendet <code>midHeaven</code>. */
   protected void calcHouses() {
     if (test.D.bug) test.D.log("HouseEqualMid ("+this.getClass()+") - calcHouses called");
     if ((latR<subRange[0])||(latR>subRange[1])) return; // do nothing

     for (int i = 0; i < NUMBER_HOUSE; i++)
         housesR[i] = CalcUtil.Mod2PI(midHeaven+CalcUtil.PIH+ANGLE_HOUSE_R*i+ siderealOffset);
   }

   /** Namen des Hausberechnungs Algorithmus.
       @return Name des Systems. */
   public String getHouseName() { return "EqualMidheaven"; }

   /** G&uuml;ltigkeit des Hausberechnungs Algorithmus.
       @return Radiant des Ranges, d.h. range(1) &lt; r &lt; range(1). */
   public double[] getValidityRange() {
      return subRange;
   }
}

