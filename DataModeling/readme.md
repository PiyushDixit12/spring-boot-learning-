#### 1. @OneToOne Relationship
   Defines a one-to-one association between two entities. For example, each User has one Address.

#### 2. @OneToMany and @ManyToOne Relationship
   Defines a one-to-many association, where one Category has many Products, and each Product belongs to one Category.

#### 3. @ManyToMany Relationship
   Defines a many-to-many association, such as many Students enrolling in many Courses.

##### Key Concepts:

**mappedBy**

Used to define the inverse side of a relationship.
Helps avoid redundancy in the database schema.
Example: In a Category-Product relationship, mappedBy is used in Category to indicate Product manages the relationship.


**cascade**

Defines how operations should propagate from the parent to the child entities.
Example Types:
CascadeType.PERSIST: When the parent is persisted, the child is also persisted.
CascadeType.MERGE: When the parent is merged, the child is also merged.
CascadeType.REMOVE: When the parent is removed, the child is also removed.
CascadeType.REFRESH: When the parent is refreshed, the child is also refreshed.
CascadeType.DETACH: When the parent is detached, the child is also detached.
CascadeType.ALL: Applies all cascade operations.

**fetch**

Defines the fetching strategy for the association.
Example Types:
FetchType.EAGER: Associated entities are fetched immediately.
FetchType.LAZY: Associated entities are fetched on demand.

**@JoinColumn**

Specifies the foreign key column linking the associated entities.