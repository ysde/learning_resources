import re

def upper_repl(match):
     return match.group(1).upper()

str = "sss_aaa_vvv"
x = re.sub(r"_(\S)", upper_repl, str)
print(x)
