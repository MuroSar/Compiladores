.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__CERO DQ 0.0
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@@aux58 DQ ?
var@@aux18 DQ ?
a@Variable@main DQ ?
salida3@Funcion@main DQ ?
ab1@Variable@main@salida30 DQ ?
var@@aux2 DQ ?
var@@aux65 DQ ?
c@Variable@main DQ ?
var@@aux90 DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
salida10@Funcion@main DQ ?
var@@aux38 DQ ?
ab1@Variable@main@salida3 DQ ?
ab1@Variable@main@salida10 DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
b@Variable@main DQ ?
salida30@Funcion@main DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@132 DB "Multiplicación var = cte anda Double", 0
cadena@@133 DB "Multiplicación var = cte no anda Double", 0
cadena@@134 DB "Multiplicación var*var = cte anda Double", 0
cadena@@135 DB "Multiplicación var*var = cte no anda Double", 0
cadena@@136 DB "Multiplicación var*-cte = cte anda Double", 0
cadena@@137 DB "Multiplicación var*-cte = cte no anda Double", 0
cadena@@138 DB "Multiplicación -cte*var = cte anda Double", 0
cadena@@139 DB "Multiplicación -cte*var = cte no anda Double", 0
cadena@@140 DB "Multiplicación -cte*-cte = cte anda Double", 0
cadena@@141 DB "Multiplicación -cte*-cte = cte no anda Double", 0
cadena@@142 DB "Multiplicación var*fn = cte anda Double", 0
cadena@@143 DB "Multiplicación var*fn = cte no anda Double", 0
cadena@@144 DB "Multiplicación fn*var = cte anda Double", 0
cadena@@145 DB "Multiplicación fn*var = cte no anda Double", 0
cadena@@146 DB "Multiplicación -cte*fn = cte anda Double", 0
cadena@@147 DB "Multiplicación -cte*fn = cte no anda Double", 0
cadena@@148 DB "Multiplicación fn*-cte = cte anda Double", 0
cadena@@149 DB "Multiplicación fn*-cte = cte no anda Double", 0
cadena@@150 DB "Multiplicación fn*fn = cte anda Double", 0
cadena@@151 DB "Multiplicación fn*fn = cte no anda Double", 0
cadena@@152 DB "Multiplicación var*fn = var anda Double", 0
cadena@@153 DB "Multiplicación var*fn = var no anda Double", 0
cadena@@154 DB "Multiplicación -cte*-cte = fn anda Double", 0
cadena@@155 DB "Multiplicación -cte*-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@_10_0 DQ -10.0
const@@_30_0 DQ -30.0
const@@30_0 DQ 30.0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
FLD const@@_3_0
FSTP ab1@Variable@main@salida3
FLD ab1@Variable@main@salida3
FSTP salida3@Funcion@main
RET
salida3 endp
salida10 proc
FLD const@@_10_0
FSTP ab1@Variable@main@salida10
FLD ab1@Variable@main@salida10
FSTP salida10@Funcion@main
RET
salida10 endp
salida30 proc
FLD const@@_30_0
FSTP ab1@Variable@main@salida30
FLD ab1@Variable@main@salida30
FSTP salida30@Funcion@main
RET
salida30 endp
start:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FMUL
FSTP var@@aux2
FLD var@@aux2
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@132, addr cadena@@132, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@133, addr cadena@@133, MB_OK
Label9:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FMUL
FSTP var@@aux11
FLD var@@aux11
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@134, addr cadena@@134, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@135, addr cadena@@135, MB_OK
Label17:
FLD const@@_10_0
FSTP a@Variable@main
FLD a@Variable@main
FLD const@@_3_0
FMUL
FSTP var@@aux18
FLD var@@aux18
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@136, addr cadena@@136, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@137, addr cadena@@137, MB_OK
Label24:
FLD const@@_3_0
FSTP b@Variable@main
FLD const@@_10_0
FLD b@Variable@main
FMUL
FSTP var@@aux25
FLD var@@aux25
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@138, addr cadena@@138, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@139, addr cadena@@139, MB_OK
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FSTP var@@aux31
FLD var@@aux31
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@140, addr cadena@@140, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@141, addr cadena@@141, MB_OK
Label37:
Label39:
FLD const@@_10_0
FSTP a@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FMUL
FSTP var@@aux43
FLD var@@aux43
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@142, addr cadena@@142, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@143, addr cadena@@143, MB_OK
Label49:
FLD const@@_3_0
FSTP b@Variable@main
CALL salida10
FLD salida10@Funcion@main
FLD b@Variable@main
FMUL
FSTP var@@aux51
FLD var@@aux51
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@144, addr cadena@@144, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@145, addr cadena@@145, MB_OK
Label57:
CALL salida3
FLD const@@_10_0
FLD salida3@Funcion@main
FMUL
FSTP var@@aux58
FLD var@@aux58
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@146, addr cadena@@146, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@147, addr cadena@@147, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion@main
FLD const@@_3_0
FMUL
FSTP var@@aux65
FLD var@@aux65
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@148, addr cadena@@148, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@149, addr cadena@@149, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion@main
FLD salida3@Funcion@main
FMUL
FSTP var@@aux73
FLD var@@aux73
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@150, addr cadena@@150, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@151, addr cadena@@151, MB_OK
Label79:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@30_0
FSTP c@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FMUL
FSTP var@@aux82
FLD var@@aux82
FLD c@Variable@main
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@152, addr cadena@@152, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@153, addr cadena@@153, MB_OK
Label88:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FSTP var@@aux90
CALL salida30
FLD salida30@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@154, addr cadena@@154, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@155, addr cadena@@155, MB_OK
Label97:
invoke ExitProcess, 0
end start