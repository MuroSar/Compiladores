.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@@aux2 DQ ?
b@Variable DQ ?
var@@aux49 DQ ?
c@Variable DQ ?
var@@aux11 DQ ?
var@@aux13 DQ ?
var@@aux37 DQ ?
d@Variable DQ ?
a@Variable DQ ?
var@@aux43 DQ ?
var@@aux25 DQ ?
e@Variable DQ ?
var@@aux31 DQ ?
Divide_bien DB "Divide bien", 0
Divide_mal DB "Divide mal", 0
Suma_bien DB "Suma bien", 0
Suma_mal DB "Suma mal", 0
Resta_bien DB "Resta bien", 0
Resta_mal DB "Resta mal", 0
Multiplica_bien DB "Multiplica bien", 0
Multiplica_mal DB "Multiplica mal", 0
const@@594_317 DQ 594.317
const@@800634_147 DQ 800634.147
const@@1347_15 DQ 1347.15
aux_mem_2bytes DW ?
const@@34812_6 DQ 34812.6
const@@588303_457 DQ 588303.457
const@@623116_057 DQ 623116.057
const@@553490_857 DQ 553490.857
const@@23_45 DQ 23.45
const@@84_982 DQ 84.982
const@@1992_8279 DQ 1992.8279
const@@802626_9749 DQ 802626.9749
const@@454723389_9 DQ 454723389.9
const@@7823_4 DQ 7823.4
const@@58123_5 DQ 58123.5
const@@14579_2 DQ 14579.2
const@@9851_9 DQ 9851.9
const@@143632820_48 DQ 143632820.48
const@@579_2 DQ 579.2
const@@9272_7 DQ 9272.7
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FNINIT
FLD const@@594_317
FSTP c@Variable
FLD const@@800634_147
FSTP d@Variable
FLD d@Variable
FLD c@Variable
FTST
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JE _division_cero
FDIV
FSTP var@@aux2
FLD var@@aux2
FSTP e@Variable
FLD e@Variable
FLD const@@1347_15
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label8
invoke MessageBox, NULL, addr Divide_bien, addr Divide_bien, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr Divide_mal, addr Divide_mal, MB_OK
Label9:
FLD const@@34812_6
FSTP a@Variable
FLD const@@588303_457
FSTP b@Variable
FLD b@Variable
FLD a@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux11
FLD var@@aux11
FSTP e@Variable
FLD b@Variable
FLD a@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FSUB
FSTP var@@aux13
FLD var@@aux13
FSTP c@Variable
FLD e@Variable
FLD const@@623116_057
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label19
invoke MessageBox, NULL, addr Suma_bien, addr Suma_bien, MB_OK
JMP Label20
Label19:
invoke MessageBox, NULL, addr Suma_mal, addr Suma_mal, MB_OK
Label20:
FLD c@Variable
FLD const@@553490_857
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label24
invoke MessageBox, NULL, addr Resta_bien, addr Resta_bien, MB_OK
JMP Label25
Label24:
invoke MessageBox, NULL, addr Resta_mal, addr Resta_mal, MB_OK
Label25:
FLD const@@23_45
FLD const@@84_982
FFREE ST(0)
FFREE ST(1)
FWAIT
FMUL
FSTP var@@aux25
FLD var@@aux25
FLD const@@1992_8279
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label30
invoke MessageBox, NULL, addr Multiplica_bien, addr Multiplica_bien, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr Multiplica_mal, addr Multiplica_mal, MB_OK
Label31:
FLD c@Variable
FLD d@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux31
FLD var@@aux31
FLD const@@802626_9749
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label36
invoke MessageBox, NULL, addr Suma_bien, addr Suma_bien, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr Suma_mal, addr Suma_mal, MB_OK
Label37:
FLD const@@454723389_9
FLD const@@7823_4
FTST
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JE _division_cero
FDIV
FSTP var@@aux37
FLD var@@aux37
FLD const@@58123_5
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label42
invoke MessageBox, NULL, addr Divide_bien, addr Divide_bien, MB_OK
JMP Label43
Label42:
invoke MessageBox, NULL, addr Divide_mal, addr Divide_mal, MB_OK
Label43:
FLD const@@14579_2
FLD const@@9851_9
FFREE ST(0)
FFREE ST(1)
FWAIT
FMUL
FSTP var@@aux43
FLD var@@aux43
FLD const@@143632820_48
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label48
invoke MessageBox, NULL, addr Multiplica_bien, addr Multiplica_bien, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr Multiplica_mal, addr Multiplica_mal, MB_OK
Label49:
FLD const@@9851_9
FLD const@@579_2
FFREE ST(0)
FFREE ST(1)
FWAIT
FSUB
FSTP var@@aux49
FLD var@@aux49
FLD const@@9272_7
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label54
invoke MessageBox, NULL, addr Resta_bien, addr Resta_bien, MB_OK
JMP Label55
Label54:
invoke MessageBox, NULL, addr Resta_mal, addr Resta_mal, MB_OK
Label55:
invoke ExitProcess, 0
FNINIT
end start