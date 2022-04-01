package p.l.e.x.u.s.sdk.usecases.root.which_su

interface ICanWhichSu {
    /**
     * Look for SU binary in the android system
     */
    fun whichSu(): Boolean
}