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
var@@aux2 DQ ?
var@@aux65 DQ ?
salida13@Funcion@main DQ ?
c@Variable@main DQ ?
var@@aux90 DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
salida10@Funcion@main DQ ?
var@@aux38 DQ ?
ab1@Variable@main@salida3 DQ ?
ab1@Variable@main@salida13 DQ ?
ab1@Variable@main@salida10 DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
b@Variable@main DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@484 DB "Suma var = -cte anda Double", 0
cadena@@485 DB "Suma var = -cte no anda Double", 0
cadena@@486 DB "Suma var+var = -cte anda Double", 0
cadena@@487 DB "Suma var+var = -cte no anda Double", 0
cadena@@488 DB "Suma var+-cte = -cte anda Double", 0
cadena@@489 DB "Suma var+-cte = -cte no anda Double", 0
cadena@@490 DB "Suma -cte+var = -cte anda Double", 0
cadena@@491 DB "Suma -cte+var = -cte no anda Double", 0
cadena@@492 DB "Suma -cte+-cte = -cte anda Double", 0
cadena@@493 DB "Suma -cte+-cte = -cte no anda Double", 0
cadena@@494 DB "Suma var+fn = -cte anda Double", 0
cadena@@495 DB "Suma var+fn = -cte no anda Double", 0
cadena@@496 DB "Suma fn+var = -cte anda Double", 0
cadena@@497 DB "Suma fn+var = -cte no anda Double", 0
cadena@@498 DB "Suma -cte+fn = -cte anda Double", 0
cadena@@499 DB "Suma -cte+fn = -cte no anda Double", 0
cadena@@500 DB "Suma fn+-cte = -cte anda Double", 0
cadena@@501 DB "Suma fn+-cte = -cte no anda Double", 0
cadena@@502 DB "Suma fn+fn = -cte anda Double", 0
cadena@@503 DB "Suma fn+fn = -cte no anda Double", 0
cadena@@504 DB "Suma var+fn = var anda Double", 0
cadena@@505 DB "Suma var+fn = var no anda Double", 0
cadena@@506 DB "Suma -cte+-cte = fn anda Double", 0
cadena@@507 DB "Suma -cte+-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@_10_0 DQ -10.0
const@@_13_0 DQ -13.0
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
salida13 proc
FLD const@@_13_0
FSTP ab1@Variable@main@salida13
FLD ab1@Variable@main@salida13
FSTP salida13@Funcion@main
RET
salida13 endp
start:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FADD
FSTP var@@aux2
FLD var@@aux2
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@2
FLD var@@aux2
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux2
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@2:
FLD var@@aux2
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@484, addr cadena@@484, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@485, addr cadena@@485, MB_OK
Label9:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FADD
FSTP var@@aux11
FLD var@@aux11
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@11
FLD var@@aux11
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux11
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@11:
FLD var@@aux11
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@486, addr cadena@@486, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@487, addr cadena@@487, MB_OK
Label17:
FLD const@@_10_0
FSTP a@Variable@main
FLD a@Variable@main
FLD const@@_3_0
FADD
FSTP var@@aux18
FLD var@@aux18
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@18
FLD var@@aux18
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux18
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@18:
FLD var@@aux18
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@488, addr cadena@@488, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@489, addr cadena@@489, MB_OK
Label24:
FLD const@@_3_0
FSTP b@Variable@main
FLD const@@_10_0
FLD b@Variable@main
FADD
FSTP var@@aux25
FLD var@@aux25
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@25
FLD var@@aux25
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux25
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@25:
FLD var@@aux25
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@490, addr cadena@@490, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@491, addr cadena@@491, MB_OK
Label31:
FLD const@@_10_0
FLD const@@_3_0
FADD
FSTP var@@aux31
FLD var@@aux31
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@31
FLD var@@aux31
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux31
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@31:
FLD var@@aux31
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@492, addr cadena@@492, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@493, addr cadena@@493, MB_OK
Label37:
Label39:
FLD const@@_10_0
FSTP a@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FADD
FSTP var@@aux43
FLD var@@aux43
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@43
FLD var@@aux43
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux43
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@43:
FLD var@@aux43
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@494, addr cadena@@494, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@495, addr cadena@@495, MB_OK
Label49:
FLD const@@_3_0
FSTP b@Variable@main
CALL salida10
FLD salida10@Funcion@main
FLD b@Variable@main
FADD
FSTP var@@aux51
FLD var@@aux51
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@51
FLD var@@aux51
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux51
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@51:
FLD var@@aux51
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@496, addr cadena@@496, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@497, addr cadena@@497, MB_OK
Label57:
CALL salida3
FLD const@@_10_0
FLD salida3@Funcion@main
FADD
FSTP var@@aux58
FLD var@@aux58
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@58
FLD var@@aux58
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux58
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@58:
FLD var@@aux58
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@498, addr cadena@@498, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@499, addr cadena@@499, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion@main
FLD const@@_3_0
FADD
FSTP var@@aux65
FLD var@@aux65
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@65
FLD var@@aux65
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux65
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@65:
FLD var@@aux65
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@500, addr cadena@@500, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@501, addr cadena@@501, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion@main
FLD salida3@Funcion@main
FADD
FSTP var@@aux73
FLD var@@aux73
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@73
FLD var@@aux73
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux73
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@73:
FLD var@@aux73
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@502, addr cadena@@502, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@503, addr cadena@@503, MB_OK
Label79:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_13_0
FSTP c@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FADD
FSTP var@@aux82
FLD var@@aux82
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@82
FLD var@@aux82
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux82
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@82:
FLD var@@aux82
FLD c@Variable@main
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@504, addr cadena@@504, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@505, addr cadena@@505, MB_OK
Label88:
FLD const@@_10_0
FLD const@@_3_0
FADD
FSTP var@@aux90
FLD var@@aux90
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@90
FLD var@@aux90
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux90
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@90:
CALL salida13
FLD var@@aux90
FLD salida13@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@506, addr cadena@@506, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@507, addr cadena@@507, MB_OK
Label97:
invoke ExitProcess, 0
end start