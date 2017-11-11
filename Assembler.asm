.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN DD  -2147483648
__MAX DD  2147483647
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
d@Variable DD ?
a@Variable DD ?
a DT ?
b@Variable DD ?
b DT ?
c DT ?
d DT ?
c@Variable DD ?
si DB si, 0
si DB si, 0
no DB no, 0
no DB no, 0
.code
(=, d, 2)
(+, 3, 5)
(=, a, [1])
(*, 8, 3)
(=, c, [3])
(-, 4, 2)
(=, b, [5])
(>, b, c)
(BF, [7], [11])
(OUT, "si", )
(BI, [13], )
(Label, Label11, )
(OUT, "no", )
(Label, Label13, )
(*, 8, 3)
(=, c, [14])
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV R1,2
MOV _d,R1
MOV R1,3
ADD R1,5
MOV #aux1,R1
CMP 1,OF
JE _overflow
MOV R1,#aux1
MOV _a,R1
MOV R1,8
MUL R1,3
MOV #aux3,R1
CMP 1,OF
JE _overflow
MOV R1,#aux3
MOV _c,R1
MOV R1,4
SUB R1,2
MOV #aux5,R1
MOV R1,#aux5
MOV _b,R1
JLE Label11
invoke MessageBox, NULL, addr si, addr si, MB_OK
JMP Label13
Label11:
invoke MessageBox, NULL, addr no, addr no, MB_OK
Label13:
MOV R1,8
MUL R1,3
MOV #aux14,R1
CMP 1,OF
JE _overflow
MOV R1,#aux14
MOV _c,R1
invoke ExitProcess, 0
end start