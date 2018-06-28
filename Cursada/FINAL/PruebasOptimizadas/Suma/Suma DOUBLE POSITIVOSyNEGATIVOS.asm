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
salida10@Funcion DQ ?
var@@aux58 DQ ?
var@@aux18 DQ ?
var@@aux2 DQ ?
var@@aux65 DQ ?
b@Variable DQ ?
salida3@Funcion DQ ?
salida7@Funcion DQ ?
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
var@@aux38 DQ ?
ab1@Variable DQ ?
a@Variable DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@78 DB "Suma var = -cte anda Double", 0
cadena@@79 DB "Suma var = -cte no anda Double", 0
cadena@@80 DB "Suma var+var = -cte anda Double", 0
cadena@@81 DB "Suma var+var = -cte no anda Double", 0
cadena@@82 DB "Suma var+-cte = cte anda Double", 0
cadena@@83 DB "Suma var+-cte = cte no anda Double", 0
cadena@@84 DB "Suma -cte+var = -cte anda Double", 0
cadena@@85 DB "Suma -cte+var = -cte no anda Double", 0
cadena@@86 DB "Suma -cte+-cte = -cte anda Double", 0
cadena@@87 DB "Suma -cte+-cte = -cte no anda Double", 0
cadena@@88 DB "Suma var+fn = -cte anda Double", 0
cadena@@89 DB "Suma var+fn = -cte no anda Double", 0
cadena@@90 DB "Suma fn+var = -cte anda Double", 0
cadena@@91 DB "Suma fn+var = -cte no anda Double", 0
cadena@@92 DB "Suma -cte+fn = -cte anda Double", 0
cadena@@93 DB "Suma -cte+fn = -cte no anda Double", 0
cadena@@94 DB "Suma fn+cte = -cte anda Double", 0
cadena@@95 DB "Suma fn+cte = -cte no anda Double", 0
cadena@@96 DB "Suma fn+fn = -cte anda Double", 0
cadena@@97 DB "Suma fn+fn = -cte no anda Double", 0
cadena@@98 DB "Suma var+fn = var anda Double", 0
cadena@@99 DB "Suma var+fn = var no anda Double", 0
cadena@@100 DB "Suma -cte+cte = fn anda Double", 0
cadena@@101 DB "Suma -cte+cte = fn no anda Double", 0
const@@3_0 DQ 3.0
const@@_10_0 DQ -10.0
const@@_7_0 DQ -7.0
aux_mem_2bytes DW ?
const@@10_0 DQ 10.0
const@@7_0 DQ 7.0
const@@_3_0 DQ -3.0
const@@_13_0 DQ -13.0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
FLD const@@3_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida3@Funcion
RET
salida3 endp
salida10 proc
FLD const@@_10_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida10@Funcion
RET
salida10 endp
salida7 proc
FLD const@@_7_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida7@Funcion
RET
salida7 endp
start:
FLD const@@_10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FADD
FST var@@aux2
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
FST c@Variable
FLD c@Variable
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@78, addr cadena@@78, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@79, addr cadena@@79, MB_OK
Label9:
FLD const@@_10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD var@@aux11
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@80, addr cadena@@80, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@81, addr cadena@@81, MB_OK
Label17:
FLD a@Variable
FLD b@Variable
FADD
FST var@@aux11
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
Label17:
FLD const@@10_0
FST a@Variable
FLD var@@aux18
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@82, addr cadena@@82, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@83, addr cadena@@83, MB_OK
Label24:
FLD a@Variable
FLD const@@_3_0
FADD
FST var@@aux18
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
Label24:
FLD const@@3_0
FST b@Variable
FLD var@@aux25
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@84, addr cadena@@84, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@85, addr cadena@@85, MB_OK
Label31:
FLD var@@aux31
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@86, addr cadena@@86, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@87, addr cadena@@87, MB_OK
Label37:
FLD const@@_10_0
FLD b@Variable
FADD
FST var@@aux25
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
Label31:
FLD const@@_10_0
FLD const@@_3_0
FADD
FST var@@aux31
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
Label37:
Label39:
FLD const@@_10_0
FST a@Variable
CALL salida3
FLD var@@aux43
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@88, addr cadena@@88, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@89, addr cadena@@89, MB_OK
Label49:
FLD a@Variable
FLD salida3@Funcion
FADD
FST var@@aux43
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
Label49:
FLD const@@3_0
FST b@Variable
CALL salida10
FLD var@@aux51
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@90, addr cadena@@90, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@91, addr cadena@@91, MB_OK
Label57:
CALL salida3
FLD var@@aux58
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@92, addr cadena@@92, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@93, addr cadena@@93, MB_OK
Label64:
CALL salida10
FLD var@@aux65
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@94, addr cadena@@94, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@95, addr cadena@@95, MB_OK
Label71:
CALL salida10
CALL salida3
FLD var@@aux73
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@96, addr cadena@@96, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@97, addr cadena@@97, MB_OK
Label79:
FLD salida10@Funcion
FLD b@Variable
FADD
FST var@@aux51
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
FLD const@@_10_0
FLD salida3@Funcion
FADD
FST var@@aux58
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
FLD salida10@Funcion
FLD const@@3_0
FADD
FST var@@aux65
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
FLD salida10@Funcion
FLD salida3@Funcion
FADD
FST var@@aux73
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
Label79:
FLD const@@_10_0
FST a@Variable
FLD const@@_7_0
FST c@Variable
CALL salida3
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@98, addr cadena@@98, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@99, addr cadena@@99, MB_OK
Label88:
FLD a@Variable
FLD salida3@Funcion
FADD
FST var@@aux82
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
Label88:
CALL salida7
FLD salida7@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@100, addr cadena@@100, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@101, addr cadena@@101, MB_OK
Label97:
invoke ExitProcess, 0
end start