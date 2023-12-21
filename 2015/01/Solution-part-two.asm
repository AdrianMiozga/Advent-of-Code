format PE console 4.0
entry start

include 'macro/import32.inc'
include 'contants.inc'

section '.text' code readable executable
start:
    push 0
    push FILE_ATTRIBUTE_NORMAL
    push OPEN_EXISTING
    push 0
    push FILE_SHARE_READ
    push GENERIC_READ
    push input
    call [CreateFileA]

    cmp eax, INVALID_HANDLE_VALUE
    je error

    mov [hFile], eax

    push 0
    push 0
    push 1024 * 8
    push buffer
    push [hFile]
    call [ReadFile]

    push [hFile]
    call [CloseHandle]

    mov esi, buffer

for:
    cmp byte [esi], 0
    je exit

    mov al, '('
    cmp byte [esi], al
    je plus
    jne minus

plus:
    inc esi
    inc [counter]
    inc [open_brackets]
    jmp for

minus:
    inc esi
    inc [counter]
    dec [open_brackets]
    js exit

    jmp for

error:
    push error_file
    call [printf_s]
    add esp, 4

    push 1
    call [ExitProcess]

exit:
    push [counter]
    push format_d
    call [printf_s]
    add esp, 8

    push 0
    call [ExitProcess]

section '.data' data readable writeable
    input           db      'input.txt', 0
    error_file      db      'Error opening input file', 0
    format_d        db      '%d', 13, 10, 0               
    open_brackets   dd      0
    counter         dd      0
    hFile           dd      ?
    buffer: times 1024 * 8 db 0

section '.idata' import data readable writeable
    library msvcrt, 'msvcrt.dll', \
        kernel32, 'Kernel32.dll'

    import msvcrt, \
        printf_s, 'printf_s'

    import kernel32, \
        CreateFileA, 'CreateFileA', \
        CloseHandle, 'CloseHandle', \
        ReadFile, 'ReadFile', \
        ExitProcess, 'ExitProcess'
