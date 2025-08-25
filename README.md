# Collections Backend (Spring)

## Pupose

An Restful API that provides CRUD functionality for a database of collections of things a user might have. This includes books, CDs, coins, etc.

## Dev notes

Currently working out the logic of inserting new items/categories/creators. A category/creator with no items associated with them is not allowed, so insertion/deletion into these tables must be handled by the equivalent item logic