### Budget DFU

Budget DFU is a simple library that allows you remap items from old saves to updated IDs.

*Example*:

you have an item with the ID of `tcraft:epic_sword`, but you want to update the registry ID to `tcraft:super_sword`.

### Bundling


### Usage

```java
// Register a remapper that converts Items with the ID of mod:apple to mod:banana.
BudgetDFU.registerDirectRemap(Registry.ITEM, new Identifier("mod", "apple"), new Identifier("mod", "banana"));

// Register a remapper that converts any namespace.
BudgetDFU.registerNamespaceRemap(Registry.ITEM, "supercraft", "coolcraft")
```
