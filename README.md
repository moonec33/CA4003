# CA4003 Compiler Construction
You will need to extend your submission for Assignment 1 to:

Generate an Abstract Syntax Tree.
Add a Symbol Table that can handle scope.
Perform a set of semtantic checks. This following is a list of typical sematic checks:
Is every identifier declared within scope before its is used?
Is no identifier declared more than once in the same scope?
Is the left-hand side of an assignment a variable of the correct type?
Are the arguments of an arithmetic operator the integer variables or integer constants?
Are the arguments of a boolean operator boolean variables or boolean constants?
Is there a function for every invoked identifier?
Does every function call have the correct number of arguments?
Is every variable both written to and read from?
Is every function called?
Generate an Intermediate Representation using 3-address code.
Feel free to add any additional semantic checks you can think of.
